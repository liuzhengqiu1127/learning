package com.lzq.spring.cloud.acquisition.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class CSVJobListener implements JobExecutionListener {
    private long startTime;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        this.startTime = System.currentTimeMillis();
        log.info("任务处理开始...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long endTime = System.currentTimeMillis();
        log.info("任务处理结束，耗时:{}ms",(endTime-startTime));
    }
}
