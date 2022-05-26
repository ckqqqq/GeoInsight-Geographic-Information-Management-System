package com.xiaoyuan.usermanager;

import com.xiaoyuan.usermanager.service.UserService;
import com.xiaoyuan.usermanager.vo.QueryParam;
import com.xiaoyuan.usermanager.vo.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName:    UserApplicationTest
 * Author:      小袁教程
 * Date:        2022/5/13 9:48
 * Description:
 */
@SpringBootTest
public class UserApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFind() {
        QueryParam param = new QueryParam();
        param.setTypename("小"); // 查询昵称前面有小字的
        //竟然可以直接set和get springboot牛逼
        R r = userService.findUserList(1, 10, param);
    }

    @Test
    public void testDeleteMore() {
        // 测试删除多个用户
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        userService.deleteUserMore(ids);
    }
}
