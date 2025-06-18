package com.gec.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.mall.goods.model.Product;
import com.gec.mall.goods.model.Spu;

public interface SpuService extends IService<Spu> {
    void saveProduct(Product product);

    Product findBySpuId(String id);



}
