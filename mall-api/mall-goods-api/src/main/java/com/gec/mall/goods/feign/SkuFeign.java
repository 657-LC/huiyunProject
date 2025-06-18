package com.gec.mall.goods.feign;


import com.gec.mall.cart.model.Cart;
import com.gec.mall.goods.model.Sku;
import com.gec.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("mall-goods")
public interface SkuFeign {
    @GetMapping("/sku/aditems/type")
    RespResult typeitems(@RequestParam Integer id);
    @DeleteMapping(value = "/sku/deleteAditems/type")
    RespResult deleteTypeItems(@RequestParam(value = "id") Integer id);

    @PutMapping(value = "/sku/updateAitems/type")
    RespResult updateTypeItems(@RequestParam(value = "id") Integer id);

    @GetMapping(value = "/sku/{id}")
    RespResult<Sku> one(@PathVariable(value = "id")String id);

    /***
     * 库存递减
     * @param carts
     * @return
     */
    @PostMapping(value = "/sku/dcount")
    RespResult decount(List<Cart> carts);
}
