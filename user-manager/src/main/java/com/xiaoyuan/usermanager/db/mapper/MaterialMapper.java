package com.xiaoyuan.usermanager.db.mapper;

import com.xiaoyuan.usermanager.db.entity.Material;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterialMapper {
    Material selectByPrimaryKey(Long id);

    void insertSelective(Material material);
}
