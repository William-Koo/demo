package com.example.service.impl;

import com.example.dao.mysql.UserDao;
import com.example.dao.oracle.UserInfoDao;
import com.example.model.DataRow;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 描述:
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: William-Koo
 * 版本: 1.0
 * 创建日期: 2017/8/17 15:30
 */
@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserDao userDao;

    @Resource
    private UserInfoDao userInfoDao;


    @Override
    public List<User> getUser() {

        return userDao.getUser();
    }

    @Override
    public User getById(String id) {

        return userDao.getById(id);
    }


    @Transactional
    @Override
    public int add(User user) {

        user.setId(StringUtil.random(20, true));
        return userDao.add(user);
    }


    @Transactional
    @Override
    public int edit(User user) {

        return userDao.edit(user);

    }


    @Transactional
    @Override
    public int delete(String id) {

        return userDao.delete(id);

    }

    @Override
    public List<DataRow> getUsers() {

        return userInfoDao.getUsers();
    }


}
