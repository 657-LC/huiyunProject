package com.gec.mall.service;

import com.gec.mall.cart.model.Cart;

import java.util.List;


public interface CartService{
    /**
     * 加入购物车
     * skuId
     * userName 用户名
     * num 加入购物车的数量
     */
    void add(String id,String userName,Integer num);


    /**
     * 购物车列表
     */
    List<Cart> list(String userName);


    /***
     * 根据ID集合查询购物车列表
     * @param ids
     * @return
     */
    Iterable<Cart> list(List<String> ids);

    //删除购物车集合
    void delete(List<String> ids);

}
