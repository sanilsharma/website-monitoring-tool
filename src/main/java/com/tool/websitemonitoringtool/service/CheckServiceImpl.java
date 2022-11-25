package com.tool.websitemonitoringtool.service;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Service;

import com.tool.websitemonitoringtool.JobScheduler;
import com.tool.websitemonitoringtool.PollTask;
import com.tool.websitemonitoringtool.pojo.Check;
import com.tool.websitemonitoringtool.repository.PollRepo;

import jakarta.transaction.Transactional;

@Service
public class CheckServiceImpl implements CheckService {

	private JobScheduler jobScheduler;
	private TaskScheduler taskScheduler;
	private PollRepo pollRepository;
	private PollService pollService;

	public CheckServiceImpl(JobScheduler jobScheduler, TaskScheduler taskScheduler, PollRepo pollRepository,
			PollService pollService) {

		this.jobScheduler = jobScheduler;
		this.taskScheduler = taskScheduler;
		this.pollRepository = pollRepository;
		this.pollService = pollService;
	}

	public void save(Check check) {

		jobScheduler.cancelScheduledPolling(check);

		try {
			taskScheduler.scheduleAtFixedRate(
					new ScheduledMethodRunnable(new PollTask(check, pollService), "startPolling"), check.getInterval());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void delete(Check check) {

		synchronized (jobScheduler.getScheduledPoller(check.getCheckId())) {
			jobScheduler.cancelScheduledPolling(check);

			pollRepository.deleteAllByChk(check);
		}
	}

}
