package com.example.component;

import com.example.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @描述: 定时任务
 * @版权: Copyright(C) 2017
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2018-01-29
 * @创建时间: 20:45
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(SchedulingConfig.class);




    @Scheduled(cron = "0 0 1 * * ?") // 每10分钟执行一次
    public void getToken() {
        LOGGER.info("这是一个定时任务: {}", DateUtil.getDate(DateUtil.YYYY_MM_DD_HH_MM_SS));
    }

}
