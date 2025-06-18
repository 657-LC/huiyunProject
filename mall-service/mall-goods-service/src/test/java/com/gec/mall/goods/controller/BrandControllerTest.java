package com.gec.mall.goods.controller;

import com.gec.mall.goods.model.Brand;
import com.gec.mall.util.RespResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BrandControllerTest {

    @Autowired
    private BrandController brandController;

    @Test
    public void testAddBrand() {
        // 创建品牌对象
        Brand brand = new Brand();
        brand.setName("魅族");
        brand.setImage("http://123.jpg");
        brand.setInitial("M");
        brand.setSort(8);

        // 调用添加方法
        RespResult result = brandController.addBran(brand);

        // 打印结果
        System.out.println("添加品牌结果：" + result);
    }
}
