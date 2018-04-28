package com.example.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;
import java.util.Set;

/**
 * @描述: 全局监听器
 * @版权: Copyright(C) 2017
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2017-11-19
 * @创建时间: 17:32
 */
@Component
@WebListener
public class GlobalListener implements ServletContextListener {

    private final Logger LOGGER = LoggerFactory.getLogger(GlobalListener.class);


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("############# 监听器初始化 #############");

        Properties props = System.getProperties();
        Set<Object> key = props.keySet();

        key.forEach(e -> LOGGER.debug(e+ ": "+ props.getProperty(e.toString())));

        LOGGER.info(String.format("JDK 版本: %s", props.getProperty("java.version")));
        LOGGER.info(String.format("项目部署路径: %s", props.getProperty("user.dir")+"/"));
        LOGGER.info(String.format("当前操作系统: %s %s", props.getProperty("os.name"), props.getProperty("os.version")));
        LOGGER.info(String.format("当前进程号: %s", props.getProperty("PID")));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        LOGGER.info("^^^^^^^^^^^^^^^ 监听器销毁 ^^^^^^^^^^^^^^^");
    }

}
