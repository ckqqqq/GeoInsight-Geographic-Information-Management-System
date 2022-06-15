package com.myproject.manager.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 Description: 继承MetaObjectHandler，对原始数据进行修改
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 配置初始创建时间
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
    }

    /**
     * 修改数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
