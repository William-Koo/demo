package com.example.util;


/**
 * 描述: 前端和后台交互结果集对象
 * 版权: Copyright (c) 2017
 * 公司:
 * 作者: William-Koo
 * 版本: 1.0
 * 创建日期: 2017/8/25 12:21
 */
public class Result {


    private String err_info = "";
    private int err_no = 0;

    private Object data;


    public String getErr_info() {
        return err_info;
    }

    public void setErr_info(String err_info) {
        this.err_info = err_info;
    }

    public int getErr_no() {
        return err_no;
    }

    public void setErr_no(int err_no) {
        this.err_no = err_no;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
