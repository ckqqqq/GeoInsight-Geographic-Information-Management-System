package com.xiaoyuan.usermanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyuan.usermanager.db.entity.User;
import com.xiaoyuan.usermanager.db.mapper.UserMapper;
import com.xiaoyuan.usermanager.service.UserService;
import com.xiaoyuan.usermanager.vo.QueryParam;
import com.xiaoyuan.usermanager.vo.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * FileName:    UserServiceImpl
 * Author:      小袁教程
 * Date:        2022/5/12 23:51
 * Description:
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public R insertUser(User user) {
        if (user == null) return R.error().message("参数错误");

        // 用户名
        String username = user.getUsername();

        // 构建条件对象, 查询是否已经存在用户名
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        wrapper.eq("username", username);
        wrapper.last("limit 1");

        // 查询判断, 如果查询出来有数据, 则不为null
        if (this.baseMapper.selectOne(wrapper) != null) R.error().message("该用户名已存在");

        // 执行插入数据操作
        return this.baseMapper.insert(user) == 0 ? R.error().message("添加用户失败") : R.ok();
    }

    @Override
    public R deleteUser(Integer id) {
        if (id == null || id <= 0) return R.error().message("参数错误");

        return  this.baseMapper.deleteById(id) == 0 ? R.error().message("删除失败") : R.ok();
    }

    @Override
    public R deleteUserMore(List<Integer> ids) {
        if (ids.size() == 0) return R.error().message("参数错误");

        return this.baseMapper.deleteBatchIds(ids) != ids.size() ? R.error().message("删除失败") : R.ok();
    }

    @Override
    public R modifyUser(User user) {
        if (user == null || user.getId() == null || user.getId() <= 0) return R.error().message("参数错误");

        return this.baseMapper.updateById(user) == 0 ? R.error().message("编辑用户失败") : R.ok();
    }

    @Override
    public R findUserList(Integer index, Integer size, QueryParam queryParam) {
        if (index == null || size == null || index <= 0 || size <= 0) {
            return R.error().message("参数错误");
        }else if (size > 10) {
            return R.error().message("一次最多10条数据");
        }

        // 构建分页对象
        Page<User> page = new Page<>(index, size);
        // 查询
        IPage<User> iPage = this.baseMapper.findUserList(page, queryParam);

        // 回传两个数据, 一个 userList --> 用户数据列表, 一个 total -> 总页数
        return R.ok().data("userList", iPage.getRecords()).data("total", iPage.getTotal());
    }

    @Override
    public R getUserInfoById(Integer id) {
        if (id == null || id <= 0) return R.error().message("参数错误");
//        System.out.print("id");
//        System.out.print(id);
        return R.ok().data("userInfo", this.baseMapper.selectById(id));
    }
}
