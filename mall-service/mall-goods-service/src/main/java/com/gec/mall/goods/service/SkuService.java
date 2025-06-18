package com.gec.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.mall.cart.model.Cart;
import com.gec.mall.goods.model.Sku;
import java.util.List;
public interface SkuService extends IService<Sku> {
    // 根据首页广告中的分类id 得到sku商品列表
    List<Sku> typeSkuItems(Integer type);
    // 用于实现对广告位的redis缓存进行清除的方法
    void deleteAditemsByTypeId(Integer type);
    // 用于实现对广告位的redis缓存进行修改的方法
    List<Sku> updateAditemsByTypeId(Integer type);
    /***
     * 库存递减
     * @param carts
     */
    void decount(List<Cart> carts);
}