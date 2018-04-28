package com.example.util;

import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @描述: 测试类
 * @版权: Copyright(C) 2018
 * @公司:
 * @作者: William-Koo
 * @版本: 1.0
 * @创建日期: 2018-01-02
 * @创建时间: 19:32
 */
public class Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) {

        int i = 0;
        while (1< 100){
            String random = StringUtil.random(36, true);
            System.err.println(random);
            i++;
        }


        //language=SQL
//        String s = "";
    }


}
