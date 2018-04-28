package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;



/**
 * @描述: 登录控制器
 * @版权: Copyright(C) 2018
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2018-04-26
 * @创建时间: 14:31
 */
@Controller("loginController")
public class LoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);




    @GetMapping("code")
    public String get() {

        return "";
    }





}
