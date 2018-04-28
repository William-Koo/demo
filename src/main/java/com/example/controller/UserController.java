package com.example.controller;


import com.example.model.DataRow;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.Result;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



/**
 * @description: 用户信息controller
 * @Copyright: (c) 2017
 * @company:
 * @author: William-Koo
 * @version: 1.0
 * @date: 2017/8/17 15:38
 */
@RequestMapping("userController")
@RestController
public class UserController {

    @Resource
    private UserService userService;



    /**
     * 查询用户列表
     * @return
     * @throws Exception
     */
    @GetMapping("user")
    public Result getUser() {
        String msg = "查询用户列表";
        Result result = new Result();

        List<User> users = userService.getUser();

        if(users != null && !users.isEmpty()){
            result.setErr_info(msg+"成功");
            result.setData(users);
        }else {
            result.setErr_info(msg+"无数据");
            result.setErr_no(-1);
        }

        return result;
    }


    /**
     * 查询指定用户
     * @param id
     * @return
     */
    @GetMapping("user/{id}")
    public Result getById(@PathVariable String id) {
        String msg = "查询单个用户信息";
        Result result = new Result();

        User user = userService.getById(id);
        if(user != null){
          result.setErr_info(msg+"成功");
          result.setData(user);
        }else {
          result.setErr_info(msg+"无数据");
          result.setErr_no(-1);
        }

        return result;
    }


    /**
     * 新增用户
     * @param user
     * @return
     */

    @PostMapping("user")
    public Result add(User user) {
        String msg = "添加用户信息";
        Result result = new Result();

        int add = userService.add(user);
        if(add > 0){
            result.setErr_info(msg+"成功");
        }else{
            result.setErr_info(msg+"失败");
            result.setErr_no(-1);
        }

        return result;
    }


    /**
     * 编辑用户
     * @param user
     * @return
     */
    @PutMapping("user")
    public Result edit(User user) {
        String msg = "编辑用户信息";
        Result result = new Result();

        int edit = userService.edit(user);
        if(edit > 0){
            result.setErr_info(msg+"成功");
        }else {
            result.setErr_info(msg+"失败");
            result.setErr_no(-1);
        }
        return result;

    }


    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("user/{id}")
    public Result delete(@PathVariable String id) {
        String msg = "删除用户信息";
        Result result = new Result();

        int delete = userService.delete(id);
        if(delete > 0){
            result.setErr_info(msg+"成功");
        }else {
            result.setErr_info(msg+"失败");
            result.setErr_no(-1);
        }

        return result;
    }



    @GetMapping("jsonp/{callback}")
    public JSONPObject jsonp(@PathVariable String callback){
        List<DataRow> users = userService.getUsers();

        return new JSONPObject(callback, users);
    }


}
