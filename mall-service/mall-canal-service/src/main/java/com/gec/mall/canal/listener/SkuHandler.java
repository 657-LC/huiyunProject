package com.gec.mall.canal.listener;

import com.alibaba.fastjson.JSON;
import com.gec.mall.goods.model.Sku;
import com.gec.mall.page.feign.PageFeign;
import com.gec.mall.search.feign.SkuSearchFeign;
import com.gec.mall.search.model.SkuEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable(value = "sku")
@Component
public class SkuHandler implements EntryHandler<Sku> {

    @Autowired
    private SkuSearchFeign skuSearchFeign;
    @Autowired
    private PageFeign pageFeign;

    /***
     * 增加产品
     * @param sku
     */
    @Override
    public void insert(Sku sku) {
        // 审核通过后才可以添加数据
        if (sku.getStatus().intValue()==1){
            // 将 Sku转为json字符串，再将json字符串 转为skuEs
            String skuJSON = JSON.toJSONString(sku);
            skuSearchFeign.add(JSON.parseObject(skuJSON, SkuEs.class));
        }

        //生成静态页面
        try {
            pageFeign.html(sku.getSpuId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 修改
     * @param before
     * @param after
     */
    @Override
    public void update(Sku before, Sku after) {
        if(after.getStatus().intValue()==2){
            // 删除索引
            skuSearchFeign.del(after.getId());
        }else{
            // 更新
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }

        //生成静态页面
        try {
            pageFeign.html(after.getSpuId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /***
     *
     * @param sku
     */
    @Override
    public void delete(Sku sku) {
        skuSearchFeign.del(sku.getId());
    }
}