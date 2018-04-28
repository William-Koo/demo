package com.example.test;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @描述: 测试基础类
 * @版权: Copyright(C) 2017
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2017-11-18
 * @创建时间: 14:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BaseTest {

    private MockMvc mvc;

    protected MultiValueMap<String, String> params;

    @Value("${server.port}")
    private String port;

    @Value("${server.tomcat.uri-encoding}")
    private String active;

    private String base = "http://127.0.0.1:8081/";

    @Resource
    private WebApplicationContext context;


    private final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);


    @Before
    public void before(){
        LOGGER.info("端口号:"+ port);
        LOGGER.info("当前使用配置文件:"+ active);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        params = new LinkedMultiValueMap<>();

    }


    /**
     * 构建请求对象
     * @param method
     * @param url
     * @throws Exception
     */
    protected void excute(HttpMethod method, String url) {

        try {
            String reaUrl = base + url;
            RequestBuilder rb = null;
            switch (method){
                case GET:
                    rb = MockMvcRequestBuilders.get(reaUrl);
                    break;

                case POST:
                    rb = MockMvcRequestBuilders.post(reaUrl).params(params);
                    break;

                case PUT:
                    rb = MockMvcRequestBuilders.put(reaUrl).params(params);
                    break;

                case DELETE:
                    rb = MockMvcRequestBuilders.delete(reaUrl);
                    break;

                default: throw new Exception("类型异常");

            }

            MvcResult mvcResult = mvc.perform(rb).andReturn();

            MockHttpServletResponse resp = mvcResult.getResponse();

            int status = resp.getStatus();
            String result = resp.getContentAsString();

            LOGGER.info("请求状态: "+ status);
            LOGGER.info("返回结果: "+ result);

        } catch (Exception e) {
            LOGGER.error("测试接口异常：", e);
        }

    }


    @After
    public void after(){
        mvc = null;
        params = null;
        context = null;
    }
}
