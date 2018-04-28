package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: 启动器
 * @Copyright: (c) 2017
 * @company:
 * @author: William-Koo
 * @version: 1.0
 * @date: 2017/6/12 11:21
 */
@EnableScheduling
@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }



}
