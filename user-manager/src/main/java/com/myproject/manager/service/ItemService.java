package com.myproject.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myproject.manager.db.entity.Item;
import com.myproject.manager.vo.QueryParam;
import com.myproject.manager.vo.R;

import java.util.List;

/**
Description: 本文件定义对应的接口，具体实现在ItemServiceImpl中
 */
public interface ItemService extends IService<Item> {

    /**
     * 添加用户
     * @param item
     * @return
     */
    R insertItem(Item item);

    /**
     * 删除单个用户
     * @param id 用户编号
     * @return
     */
    R deleteItem(String id);

    /**
     * 删除多个用户
     * @param ids 用户编号集合
     * @return
     */
    R deleteItemMore(List<String> ids);

    /**
     * 编辑用户
     * @param item
     * @return
     */
    R modifyItem(Item item);

    /**
     * 分页查询用户列表
     * @param index 当前页
     * @param size 每页显示数量
     * @param queryParam 筛选条件对象
     * @return
     */
    R findItemList(Integer index, Integer size, QueryParam queryParam);

    /**
     * 查询单个用户详细信息
     * @param id 用户编号
     * @return
     */
    R getItemInfoById(String id);
}
