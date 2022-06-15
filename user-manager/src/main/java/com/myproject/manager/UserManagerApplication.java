package com.myproject.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
UserManagerApplication
 Description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.myproject.manager.db.mapper"})
//定义BaseMapper的路径
@ComponentScan({"com.myproject"})
//的作用就是根据定义的扫描路径，把符合扫描规则的类装配到spring容器中，后面连接的是myproject(project_name),位于这个路径下的所有类都会加入spring中
public class UserManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class, args);
    }
}
