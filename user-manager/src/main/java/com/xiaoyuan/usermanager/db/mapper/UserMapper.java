package com.xiaoyuan.usermanager.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoyuan.usermanager.db.entity.User;
import com.xiaoyuan.usermanager.vo.QueryParam;
import org.springframework.stereotype.Repository;

/**
 * FileName:    UserMapper
 * Author:      小袁教程
 * Date:        2022/5/12 23:25
 * Description: DAO层
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户列表
     * @param page 分页对象
     * @param queryParam 筛选条件
     * @return
     */
    IPage<User> findUserList(Page<User> page, QueryParam queryParam);
}
