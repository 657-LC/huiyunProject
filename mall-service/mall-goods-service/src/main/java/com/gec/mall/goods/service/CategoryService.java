package com.gec.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.mall.goods.model.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> querByParentId(Integer pid);
}
