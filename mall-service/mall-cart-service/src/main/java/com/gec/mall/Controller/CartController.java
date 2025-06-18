package com.gec.mall.Controller;


import com.gec.mall.cart.model.Cart;
import com.gec.mall.service.CartService;
import com.gec.mall.util.RespResult;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin
public class CartController {
    @Autowired
    public CartService cartService;


    /**
     * 添加购物车
     */
    @GetMapping(value = "{id}/{num}")
    public RespResult add(@PathVariable(value = "id")String id,@PathVariable(value = "num")Integer num){
        //用户名
        String userName = "lishi";
        //加入购物车
        cartService.add(id,userName,num);
        return RespResult.ok();
    }

    /**
     * 购物车列表
     */
    @GetMapping(value = "/list")
    public RespResult<List<Cart>> list(){
        String userName = "lishi";
        List<Cart> carts = cartService.list(userName);
        return RespResult.ok(carts);
    }

    /***
     * 购物车数据
     */
    @PostMapping(value = "/list")
    public RespResult<List<Cart>> list(@RequestBody List<String> ids){
        //购物车集合
        List<Cart> carts = Lists.newArrayList(cartService.list(ids));
        return RespResult.ok(carts);
    }

    /**
     * 删除指定的购物车
     */
    @DeleteMapping
    public RespResult delete(@RequestBody List<String> ids){
        //删除购物车集合
        cartService.delete(ids);
        return RespResult.ok();
    }




}
