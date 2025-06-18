package com.gec.mall.goods.feign;

import com.gec.mall.goods.model.Product;
import com.gec.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-goods")
public interface SpuFeign {

    /***
     * 根据ID查询
     */
    @GetMapping(value = "/spu/product/{id}")
    RespResult<Product> one(@PathVariable(value = "id")String id);
}