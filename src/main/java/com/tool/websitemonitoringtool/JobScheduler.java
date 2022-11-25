package com.tool.websitemonitoringtool;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Component;

import com.tool.websitemonitoringtool.pojo.Check;


@Component
@Configuration
public class JobScheduler {

    private final Map<Check, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    public void cancelScheduledPolling(Check checkId) {
        ScheduledFuture<?> scheduledFuture = scheduledTasks.remove(checkId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public ScheduledFuture<?> getScheduledPoller(Integer checkId) {
        return scheduledTasks.get(checkId);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new CustomThreadPoolTaskScheduler();
    }

    class CustomThreadPoolTaskScheduler extends ThreadPoolTaskScheduler {

        @Override
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
            ScheduledFuture<?> future = super.scheduleAtFixedRate(task, period);

            ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) task;
            scheduledTasks.put(((PollTask) runnable.getTarget()).getCheck(), future);

            return future;
        }

        @Override
        public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period) {
            ScheduledFuture<?> future = super.scheduleAtFixedRate(task, startTime, period);

            ScheduledMethodRunnable runnable = (ScheduledMethodRunnable) task;
            scheduledTasks.put(((PollTask) runnable.getTarget()).getCheck(), future);

            return future;
        }
    }
}