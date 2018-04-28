package com.example.controller;

import com.example.test.BaseTest;
import com.example.util.DateUtil;
import org.junit.Test;
import org.springframework.http.HttpMethod;

/**
 * @描述: 测试用户 controller
 * @版权: Copyright(C) 2017
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2017-11-18
 * @创建时间: 21:20
 */
public class TestUserController extends BaseTest {


    private final String BASE_URL = "userController/user/";


    @Test
    public void testGet() throws Exception {

        excute(HttpMethod.GET, BASE_URL);

    }


    @Test
    public void testGetOne() throws Exception {

        excute(HttpMethod.GET, BASE_URL + "1f100f189n1aoo2gl4ho00rxes9149");

    }


    @Test
    public void testAdd() throws Exception {

        params.add("name", "坎坎坷坷扩");
        params.add("birthday", DateUtil.getDate(DateUtil.YYYY_MM_DD));
        params.add("money", "145678");

        excute(HttpMethod.POST, BASE_URL);

    }



    @Test
    public void testEdit() throws Exception {

        params.add("id", "Nw0yy52ELJPg8N7W2O10");
        params.add("name", "这是测试名字");
        params.add("birthday", DateUtil.getDate(DateUtil.YYYY_MM_DD));
        params.add("money", "123123");

        excute(HttpMethod.PUT, BASE_URL);

    }


    @Test
    public void testDel() throws Exception {

        excute(HttpMethod.DELETE, BASE_URL + "Nw0yy52ELJPg8N7W2O10");

    }

}
