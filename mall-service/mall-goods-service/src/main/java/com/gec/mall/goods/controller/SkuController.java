package com.gec.mall.goods.controller;

import com.gec.mall.cart.model.Cart;
import com.gec.mall.goods.model.Sku;
import com.gec.mall.goods.service.SkuService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sku")
@CrossOrigin
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     *  指定分类下的推广 产品 列表
     * @return
     */
    @RequestMapping("/aditems/type")
    public List<Sku> typeitems(@RequestParam Integer id){
       
        // 查询 
        List<Sku> adSkuItems = skuService.typeSkuItems(id);
        return adSkuItems;
    }

    @DeleteMapping(value = "/deleteAditems/type")
    public RespResult deleteTypeItems(@RequestParam(value = "id") Integer id){
        //清理缓存
        skuService.deleteAditemsByTypeId(id);
        return RespResult.ok();
    }

    @PutMapping(value = "/updateAitems/type")
    public RespResult updateTypeItems(@RequestParam(value = "id") Integer id){
        //修改缓存
        skuService.updateAditemsByTypeId(id);
        return RespResult.ok();
    }

    /**
     * 根据ID获取sku
     */
    @GetMapping(value = "/{id}")
    public RespResult<Sku> one(@PathVariable(value = "id")String id){
        Sku sku = skuService.getById(id);
        return RespResult.ok(sku);
    }

    /***
     * 库存递减
     * @param carts
     * @return
     */
    @PostMapping(value = "/dcount")
    public RespResult decount(@RequestBody List<Cart> carts){
        skuService.decount(carts);
        return RespResult.ok();
    }
}