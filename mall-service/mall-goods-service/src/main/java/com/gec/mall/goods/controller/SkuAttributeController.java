package com.gec.mall.goods.controller;


import com.gec.mall.goods.model.SkuAttribute;
import com.gec.mall.goods.service.SkuAttributeService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/skuAttribute")
@CrossOrigin
public class SkuAttributeController {


    @Autowired
    private SkuAttributeService skuAttributeService;
    /***
     * 根据分类ID查询
     */
    @GetMapping(value = "/category/{id}")
    public RespResult<List<SkuAttribute>> categoryAttributeList(@PathVariable(value = "id")Integer id){
        //根据分类ID查询属性参数
        List<SkuAttribute> skuAttributes = skuAttributeService.queryList(id);
        return RespResult.ok(skuAttributes);
    }
}