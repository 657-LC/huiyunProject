package com.gec.mall.goods.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.mall.cart.model.Cart;
import com.gec.mall.goods.mapper.AdItemsMapper;
import com.gec.mall.goods.mapper.SkuMapper;
import com.gec.mall.goods.model.AdItems;
import com.gec.mall.goods.model.Sku;
import com.gec.mall.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@CacheConfig(cacheNames = "ad-item-skus")
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {


    @Autowired
    public SkuMapper skuMapper;
    @Autowired
    public AdItemsMapper adItemsMapper;


    @Cacheable(key = "#type")
//    @CachePut(cacheNames = "ad-item-skus",key = "#type")
    @Override
    public List<Sku> typeSkuItems(Integer type) {
        System.out.println("数据库查询。。。");
        //1查询所有分类下的推广
        QueryWrapper<AdItems> adItemsQueryWrapper = new QueryWrapper<>();
        adItemsQueryWrapper.eq("type",type);

        List<AdItems> adItems = this.adItemsMapper.selectList(adItemsQueryWrapper);
        //2获取所有skuId
        List<String> skuIds = adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());
        //3批量查询skuids
        List<Sku> skus = skuMapper.selectBatchIds(skuIds);
        return skus;
    }

    @CacheEvict(key = "#type")
    @Override
    public void deleteAditemsByTypeId(Integer type) {
        System.out.println("用 @CacheEvict 注解 执行了删除redis缓存操作....");
    }

    // 缓存修改
    @CachePut(key = "#type")
    @Override
    public List<Sku> updateAditemsByTypeId(Integer type) {
        QueryWrapper<AdItems> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type);

        //1.先根据type去查询下面所有的广告列表
        List<AdItems> adItemsList = this.adItemsMapper.selectList(queryWrapper);

        //2. 从 List<AdItems> 取出所有的skuids 集合
        List<String> skuids = adItemsList.stream().map(adItems -> adItems.getSkuId()).collect(Collectors.toList());


        //3.再根据skuids 查询下面所有的sku商品集合
        List<Sku> skuList = this.skuMapper.selectBatchIds(skuids);



        return skuList;
    }


    /**
     * 库存递减
     * @param carts
     */
    @Transactional(rollbackFor = Exception.class)//开启本地事务
    @Override
    public void decount(List<Cart> carts) {
        for (Cart cart : carts) {
            //语句级控制，防止超卖
            int count = skuMapper.decount(cart.getSkuId(), cart.getNum());
            if (count <= 0) {
                throw new RuntimeException("库存不足！");
            }
        }
    }

}
