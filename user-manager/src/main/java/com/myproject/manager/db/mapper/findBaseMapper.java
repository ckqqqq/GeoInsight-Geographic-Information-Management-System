package com.myproject.manager.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myproject.manager.vo.QueryParam;
import com.myproject.manager.db.entity.Item;
import org.springframework.stereotype.Repository;

/**
 Description: DAO层
 */
//这个函数是Dao层baseMapper基础映射接口，可以直接用
//下面定义一些
@Repository
public interface findBaseMapper extends BaseMapper<Item> {

    /**
     * 查询用户列表
     * @param page 分页对象
     * @param queryParam 筛选条件
     * @return
     */
    IPage<Item> findItemList(Page<Item> page, QueryParam queryParam);
//调用方式 this.baseMapper.findItemList(page, queryParam);
}
