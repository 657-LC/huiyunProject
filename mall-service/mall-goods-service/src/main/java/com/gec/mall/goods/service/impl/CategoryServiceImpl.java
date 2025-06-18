package com.gec.mall.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.mall.goods.mapper.CategoryMapper;
import com.gec.mall.goods.model.Category;
import com.gec.mall.goods.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<Category> querByParentId(Integer pid) {
        //条件封装
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",pid);
        return this.baseMapper.selectList(queryWrapper);
    }
}
