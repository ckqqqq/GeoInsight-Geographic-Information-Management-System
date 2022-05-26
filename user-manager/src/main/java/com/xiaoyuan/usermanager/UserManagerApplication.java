package com.xiaoyuan.usermanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * FileName:    UserManagerApplication
 * Author:      小袁教程
 * Date:        2022/5/12 22:51
 * Description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.xiaoyuan.usermanager.db.mapper"})
@ComponentScan({"com.xiaoyuan"})
public class UserManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class, args);
    }
}
