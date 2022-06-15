package com.myproject.manager.db.mapper;

import com.myproject.manager.db.entity.Material;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterialMapper {
    Material selectByPrimaryKey(Long id);

    void insertSelective(Material material);
}