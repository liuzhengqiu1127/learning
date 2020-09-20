package com.lzq.spring.cloud.acquisition.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScheduledTaskService {
    @Resource
    JobLauncher jobLauncher;
    @Resource
    Job importJob;
    JobParameters jobParameters;

    @Scheduled(fixedRate = 5000)
    public void execute() throws Exception{
        jobParameters = new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
        this.jobLauncher.run(importJob, jobParameters);
    }
}
