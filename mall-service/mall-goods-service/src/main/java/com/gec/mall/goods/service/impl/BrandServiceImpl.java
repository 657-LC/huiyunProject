package com.gec.mall.goods.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.mall.goods.mapper.BrandMapper;
import com.gec.mall.goods.model.Brand;
import com.gec.mall.goods.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    /***
     * 分页查询
     * @param brand
     * @return
     */
    @Override
    public Page<Brand> queryPageList(Long currentPage, Long size, Brand brand) {
        // 封装查询条件
        Page<Brand> page = this.baseMapper.selectPage(
                new Page<Brand>(currentPage, size),
                new QueryWrapper<Brand>()
                        .like("name", brand.getName()));
        return page;
    }

    @Override
    public List<Brand> queryByCategoryId(Integer id) {
        //1.根据分类id查询对应的的商品id集合
        List<Integer> brandIds = this.baseMapper.queryBrandIds(id);

        //2.根据品牌id集合查询品牌列表
        List<Brand> brands = this.baseMapper.selectBatchIds(brandIds);
        return brands;
    }

    @Override
    public List<Brand> queryList(Brand brand) {
        //多条件构造器
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(brand.getName())){
            queryWrapper.like("name",brand.getName());
        }
        if (!StringUtils.isEmpty(brand.getInitial())){
            queryWrapper.eq("initial",brand.getInitial());
        }
        return this.baseMapper.selectList(queryWrapper);
    }


}




































