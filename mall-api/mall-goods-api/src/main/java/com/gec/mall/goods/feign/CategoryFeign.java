package com.gec.mall.goods.feign;

import com.gec.mall.goods.model.Category;
import com.gec.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("mall-goods")
public interface CategoryFeign {

    @GetMapping(value = "/category/{id}")
    public RespResult<Category> one(@PathVariable(value = "id")Integer id);

}
