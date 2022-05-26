package com.xiaoyuan.usermanager.controller;

import com.xiaoyuan.usermanager.db.entity.User;
import com.xiaoyuan.usermanager.service.UserService;
import com.xiaoyuan.usermanager.vo.QueryParam;
import com.xiaoyuan.usermanager.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FileName:    UserController
 * Author:      小袁教程
 * Date:        2022/5/13 9:18
 * Description:
 */
//这个似乎是负责拦截所有的前端的request的一个拦截器，
@RestController
//下面这个确定request的根路径，在WebMVCConfig第二十行里面设置拦截器路径
@RequestMapping("/item")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // 添加用户 对R本身进行操作
    @PostMapping("")
    public R insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    // 删除单个用户
    @DeleteMapping("{id}")
    public R deleteUser(@PathVariable(value = "id") Integer id) {
        return userService.deleteUser(id);
    }

    // 删除多个用户
    @DeleteMapping("")
    public R deleteUserMore(@RequestBody List<Integer> ids) {
        return userService.deleteUserMore(ids);
    }

    // 编辑用户
    @PutMapping("")
    public R modifyUser(@RequestBody User user) {
        return userService.modifyUser(user);
    }

    // 查询用户列表
    @PostMapping("{index}/{size}")
    public R findUserList(@PathVariable(value = "index") Integer index,
                          @PathVariable(value = "size") Integer size,
                          @RequestBody(required = false) QueryParam queryParam) {
        return userService.findUserList(index, size, queryParam);
    }

    // 根据用户编号查询用户信息
    @PostMapping("{id}")
    public R getUserInfo(@PathVariable(value = "id") Integer id) {
        return userService.getUserInfoById(id);
    }
}
