package com.myproject.manager;

import com.myproject.manager.vo.QueryParam;
import com.myproject.manager.vo.R;
import com.myproject.manager.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class ItemApplicationTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void testFind() {
        QueryParam param = new QueryParam();
        param.setTypename("皮"); // 查询昵称前面有小字的
        //竟然可以直接set和get springboot牛逼
        R r = itemService.findItemList(1, 10, param);
    }

    @Test
    public void testDeleteMore() {
        // 测试删除多个用户
        List<String> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(2);
//        ids.add(3);
        itemService.deleteItemMore(ids);
    }
}
