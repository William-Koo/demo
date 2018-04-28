package com.example.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


/**
 * @description: 跨域过滤器
 * @Copyright: (c) 2017
 * @company:
 * @author: William-Koo
 * @version: 1.0
 * @date: 2017/11/18 01:12
 */
@Component
@Order(value = 1)
@WebFilter(filterName = "CrossFilter", urlPatterns = "/*")
public class CrossFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(CrossFilter.class);

    @Value("${cross.url}")
    private String url;


    @Override
    public void init(FilterConfig config) {
        LOGGER.info("~~~~~~~~~~~~~~~ 过滤器初始化 ~~~~~~~~~~~~~~~");

        Enumeration<String> params = config.getInitParameterNames();

        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String value = config.getInitParameter(key);
            LOGGER.info(key + ": " + value);
        }
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        LOGGER.info("********************* 过滤器被使用 ********************");


        HttpServletResponse response = (HttpServletResponse) resp;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Expose-Headers", "*");

        chain.doFilter(req, resp);

    }


    @Override
    public void destroy() {
        LOGGER.info("！！！！！！！！！！！！过滤器被销毁！！！！！！！！！！！！");
    }
}
