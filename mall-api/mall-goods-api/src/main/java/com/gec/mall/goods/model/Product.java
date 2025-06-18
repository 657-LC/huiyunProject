package com.gec.mall.goods.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 大飞
 * @since 1.0
 ****/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    // Spu
    private Spu spu;
    // Sku
    private List<Sku> skus;
}
