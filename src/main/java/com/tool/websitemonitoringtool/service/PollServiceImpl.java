package com.tool.websitemonitoringtool.service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.base.Stopwatch;
import com.tool.websitemonitoringtool.JobScheduler;
import com.tool.websitemonitoringtool.Utilities;
import com.tool.websitemonitoringtool.pojo.Check;
import com.tool.websitemonitoringtool.pojo.Poll;
import com.tool.websitemonitoringtool.pojo.Status;
import com.tool.websitemonitoringtool.repository.PollRepo;


@Service
public class PollServiceImpl implements PollService {

    private final int TIMEOUT = 5000;

    private PollRepo pollRepository;
    private JobScheduler jobScheduler;

    public PollServiceImpl(PollRepo pollRepository, JobScheduler jobScheduler) {
        this.pollRepository = pollRepository;
        this.jobScheduler = jobScheduler;
    }

    @Override
    public void pollGivenCheck(Check chk) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Status status = Utilities.pingURL(chk.getUri(), TIMEOUT);
        stopwatch.stop();

        ScheduledFuture<?> lock = jobScheduler.getScheduledPoller(chk.getCheckId());
        if (lock == null) return;
        synchronized (lock) {
      
            if (jobScheduler.getScheduledPoller(chk.getCheckId()) == null) return;
            pollRepository.save(new Poll(
                    status,
                    ZonedDateTime.now(Clock.systemUTC()),
                    stopwatch.elapsed(TimeUnit.MILLISECONDS),
                    chk
            ));
        }
    }
}
