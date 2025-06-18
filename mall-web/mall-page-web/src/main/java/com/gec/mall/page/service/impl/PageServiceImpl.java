package com.gec.mall.page.service.impl;

import com.alibaba.fastjson.JSON;
import com.gec.mall.goods.feign.CategoryFeign;
import com.gec.mall.goods.feign.SpuFeign;
import com.gec.mall.goods.model.Category;
import com.gec.mall.goods.model.Product;
import com.gec.mall.goods.model.Sku;
import com.gec.mall.page.service.PageService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private SpuFeign spuFeign;

    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${pagepath}")
    private String pagepah;

    //生成静态燕页

    @Override
    public void html(String id) throws FileNotFoundException, UnsupportedEncodingException {
        //加载数据
        Map<String, Object> dateMap = dataLoad(id);


        //创建Thymeleaf容器对象
        Context context = new Context();
        //设置页面数据模型
        context.setVariables(dateMap);
        //文件名字id.html
        File dest = new File(pagepah,id+".html");
        PrintWriter writer = new PrintWriter(dest,"UTF-8");
        //生成页面
        templateEngine.process("item",context,writer);
    }

    //数据加载
    public Map<String,Object> dataLoad(String id) {
        //查询商品数据
        RespResult<Product> respResult = spuFeign.one(id);
        Product product = respResult.getData();
        if (product != null) {
            //数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();

            //spu
            dataMap.put("spu", product.getSpu());
            //图片
            dataMap.put("images",
                    product.getSpu().getImages().split(","));


            //属性
            dataMap.put("attrs", JSON.parseObject(product.getSpu().getAttributeList()));

            //三级分类查询
            RespResult<Category> one =
                    categoryFeign.one(product.getSpu().getCategoryOneId());
            RespResult<Category> two =
                    categoryFeign.one(product.getSpu().getCategoryTwoId());
            RespResult<Category> three =
                    categoryFeign.one(product.getSpu().getCategoryThreeId());
            dataMap.put("one", one.getData());
            dataMap.put("two", two.getData());
            dataMap.put("three", three.getData());

            //sku集合转JSON
            List<Sku> skus = product.getSkus();
            List<Map<String, Object>> skulist = new ArrayList<>();
            for (Sku sku : skus) {
                Map<String, Object> skuMap = new HashMap<>();
                skuMap.put("id", sku.getId());
                skuMap.put("name", sku.getName());
                skuMap.put("price", sku.getPrice());
                skuMap.put("attr", sku.getSkuAttribute());

                //添加到集合中
                skulist.add(skuMap);

            }
            dataMap.put("skulist", skulist);
            return dataMap;
        }
        return null;
        }


}
