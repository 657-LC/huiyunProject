package com.gec.mall.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.mall.goods.model.Brand;

import java.util.List;


public interface BrandService extends IService<Brand> {
    Page<Brand> queryPageList(Long currentPage, Long size, Brand brand);
    // 根据分类的ID查询品牌列表
    List<Brand> queryByCategoryId(Integer id);
    List<Brand> queryList(Brand brand);
}
