package com.gec.mall.page.feign;

import com.gec.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-web-page")
public interface PageFeign {

    /***
     * 生成静态页
     */
    @GetMapping(value = "/page/{id}")
    RespResult html(@PathVariable(value = "id")String id) throws Exception;
}