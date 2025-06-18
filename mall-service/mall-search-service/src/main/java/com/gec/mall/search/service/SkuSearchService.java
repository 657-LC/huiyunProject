package com.gec.mall.search.service;

import com.gec.mall.search.model.SkuEs;

import java.util.Map;

public interface SkuSearchService {
    //添加索引
    void add(SkuEs skuEs);
    //删除索引
    void del(String id);
    Map<String,Object> search(Map<String, Object> searchMap);
}