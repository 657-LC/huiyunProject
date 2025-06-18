package com.gec.mall.goods.controller;


import com.gec.mall.goods.model.Category;
import com.gec.mall.goods.model.Product;
import com.gec.mall.goods.service.SpuService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {
    @Autowired
    private SpuService spuService;


    /**
     * 保存
     */
    @PostMapping(value = "/save")
    public RespResult save(@RequestBody Product product){
        //保存
        spuService.saveProduct(product);
        return RespResult.ok();
    }

    /**
     * 根据id查询product
     */
    @GetMapping(value = "/product/{id}")
    public RespResult<Category> one(@PathVariable(value = "id")String id){
        Product product = spuService.findBySpuId(id);
        return RespResult.ok(product);
    }

//    /**
//     * 根据id查询product
//     */
//    @GetMapping(value = "/product/{id}")
//    public RespResult<Product> findProductById(@PathVariable(value = "id")String id){
//        Product product = spuService.findBySpuId(id);
//        return RespResult.ok(product);
//    }

}
