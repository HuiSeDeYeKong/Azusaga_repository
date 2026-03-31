package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务类
 */
@Component
@Slf4j
public class MyTask {

    @Scheduled(cron = "0 0/1 * * * ?") // 每分钟执行一次
    public void doTask() {
        log.info("执行定时任务:{}", new Date());
    }

}
