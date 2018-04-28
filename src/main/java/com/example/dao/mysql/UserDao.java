package com.example.dao.mysql;

import com.example.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: William-Koo
 * 版本: 1.0
 * 创建日期: 2017/8/17 15:07
 */
public interface UserDao {

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



}
