package com.gec.mall.search.service.Impl;

import com.alibaba.fastjson.JSON;
import com.gec.mall.search.mapper.SkuSearchMapper;
import com.gec.mall.search.service.SkuSearchService;
import com.gec.mall.search.model.SkuEs;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    @Autowired
    private SkuSearchMapper skuSearchMapper;

    /***
     * 单个导入ES
     * @param skuEs
     */
    @Override
    public void add(SkuEs skuEs) {
        // 获取属性信息
        String skuAttributes = skuEs.getSkuAttribute();

        // 将属性填充到attrMap中
        if (!StringUtils.isEmpty(skuAttributes))
        {
            skuEs.setAttrMap(JSON.parseObject(skuAttributes, Map.class));
        }
        System.out.println("-- SkuSearchServiceImpl---- public void add(SkuEs skuEs)  添加索引库数据操作执行了 - ");
        this.skuSearchMapper.save(skuEs);
    }

    /***
     * 根据ID删除
     * @param id
     */
    @Override
    public void del(String id) {
        System.out.println("-- SkuSearchServiceImpl---- public void delete(String id)  删除索引库数据操作执行了 - ");
        this.skuSearchMapper.deleteById(id);

    }

    /**
     * 商品搜索
     */
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        //条件封装
        NativeSearchQueryBuilder queryBuilder = queryBuilder(searchMap);

        //执行搜索
        Page<SkuEs> result = skuSearchMapper.search(queryBuilder.build());

        //结果集
        List<SkuEs> list = result.getContent();
        //中记录数
        long totalElements = result.getTotalElements();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", list);
        resultMap.put("totalElements", totalElements);
        return resultMap;
    }

    /***
     * 搜索条件组装
     */
    public NativeSearchQueryBuilder queryBuilder(Map<String,Object> searchMap){
        //QueryBuilder构建
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //条件判断
        if(searchMap!=null && searchMap.size()>0){
            //关键词
            Object keywords =searchMap.get("keywords");
            if(!StringUtils.isEmpty(keywords)){
                queryBuilder.withQuery(QueryBuilders.termQuery("name",keywords.toString()));
            }
        }
        return queryBuilder;
    }
}