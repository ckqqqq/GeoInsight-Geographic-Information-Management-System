package com.myproject.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myproject.manager.db.entity.Item;
import com.myproject.manager.db.mapper.findBaseMapper;
import com.myproject.manager.vo.QueryParam;
import com.myproject.manager.vo.R;
import com.myproject.manager.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 Description: 对Service的具体实现
 */
@Service
@Transactional
public class ItemServiceImpl extends ServiceImpl<findBaseMapper, Item> implements ItemService {

    @Override
    public R insertItem(Item item) {
        if (item == null) return R.error().message("参数错误");
        System.out.println("item"+item.toString());

        // 用户名
        String itemname = item.getItemname();
        //虽然我没有定义get函数但是他竟然能够自动生成yyds

        // 构建条件对象, 查询是否已经存在用户名
        QueryWrapper<Item> wrapper = new QueryWrapper<>();
        //到Wrapper中国查找Item
        wrapper.select("id");
        wrapper.eq("itemname", itemname);
        wrapper.last("limit 1");

        // 查询判断, 如果查询出来有数据, 则不为null
        if (this.baseMapper.selectOne(wrapper) != null) R.error().message("该用户名已存在");

        // 执行插入数据操作
        return this.baseMapper.insert(item) == 0 ? R.error().message("添加用户失败") : R.ok();
    }

    @Override
    public R deleteItem(String id) {
        if (id == null ) return R.error().message("参数错误");

        return  this.baseMapper.deleteById(id) == 0 ? R.error().message("删除失败") : R.ok();
    }

    @Override
    public R deleteItemMore(List<String> ids) {
        if (ids.size() == 0) return R.error().message("参数错误");

        return this.baseMapper.deleteBatchIds(ids) != ids.size() ? R.error().message("删除失败") : R.ok();
    }

    @Override
    public R modifyItem(Item item) {
        if (item == null || item.getId() == null) return R.error().message("参数错误");

        return this.baseMapper.updateById(item) == 0 ? R.error().message("编辑用户失败") : R.ok();
    }

    @Override
    public R findItemList(Integer index, Integer size, QueryParam queryParam) {
        if (index == null || size == null || index <= 0 || size <= 0) {
            return R.error().message("参数错误");
        }else if (size > 10) {
            return R.error().message("一次最多10条数据");
        }

        // 构建分页对象
        Page<Item> page = new Page<Item>(index, size);
        // 查询
        System.out.println("index:size"+Integer.toString(index)+Integer.toString(size));
        System.out.println(page.toString()+"\n\n\n\n");
        IPage<Item> iPage = this.baseMapper.findItemList(page, queryParam);
        System.out.println(iPage.getRecords()+"\n\n\n\n");
        // 回传两个数据, 一个 itemList --> 用户数据列表, 一个 total -> 总页数
        return R.ok().data("itemList", iPage.getRecords()).data("total", iPage.getTotal());
    }

    @Override
    public R getItemInfoById(String id) {
        if (id == null ) return R.error().message("参数错误");
//        System.out.print("id");
//        System.out.print(id);
        return R.ok().data("itemInfo", this.baseMapper.selectById(id));
    }
}
