package com.gec.mall.search.controller;

import com.gec.mall.search.model.SkuEs;
import com.gec.mall.search.service.SkuSearchService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/search")
public class SkuSearchController {

    @Autowired
    private SkuSearchService skuSearchService;

    /****
     * 单个商品导入
     */
    @PostMapping(value = "/add")
    public RespResult add(@RequestBody SkuEs skuEs){
        skuSearchService.add(skuEs);
        return RespResult.ok();
    }

    /***
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/del/{id}")
    public RespResult del(@PathVariable String id){
        skuSearchService.del(id);
        return RespResult.ok();
    }

    /****
     * 商品搜索
     */
    @GetMapping
    public RespResult<Map<String,Object>> search(@RequestParam(required = false) Map<String,Object> searchMap){
        Map<String,Object> result = skuSearchService.search(searchMap);
        return RespResult.ok(result);
    }
}