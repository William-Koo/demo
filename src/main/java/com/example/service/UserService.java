package com.example.service;


import com.example.model.DataRow;
import com.example.model.User;

import java.util.List;

/**
 * 描述: 用户 service
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: William-Koo
 * 版本: 1.0
 * 创建日期: 2017/8/17 15:29
 */
public interface UserService {

    /**
     *
     * @return
     */
    List<User> getUser();


    /**
     *
     * @param id
     * @return
     */
    User getById(String id);


    /**
     *
     * @param user
     */
    int add(User user);


    /**
     *
     * @param user
     */
    int edit(User user);


    /**
     *
     * @param id
     */
    int delete(String id);


    /**
     * 二次业务查询用户信息
     * @return
     */
    List<DataRow> getUsers();



}
