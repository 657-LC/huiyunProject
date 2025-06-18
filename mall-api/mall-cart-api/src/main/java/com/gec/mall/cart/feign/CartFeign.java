package com.gec.mall.cart.feign;

import com.gec.mall.cart.model.Cart;
import com.gec.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "mall-cart")
public interface CartFeign {

    /***
     * 购物车数据
     */
    @PostMapping(value = "/cart/list")
    RespResult<List<Cart>> list(@RequestBody List<String> ids);

    /**
     * 删除购物测
     */
    @DeleteMapping(value = "/cart")
    RespResult delete(@RequestBody List<String> ids);


}