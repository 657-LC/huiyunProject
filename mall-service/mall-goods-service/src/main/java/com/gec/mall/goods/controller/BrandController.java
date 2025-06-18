package com.gec.mall.goods.controller;


import com.gec.mall.goods.model.Brand;
import com.gec.mall.goods.service.BrandService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    @PostMapping("/addBrand")
    public RespResult addBran(@RequestBody Brand brand){
        this.brandService.save(brand);
        return RespResult.ok();
    }

    //根据id去得到一个商品信息
    @GetMapping("/getBrandById/{id}")
    public RespResult getBrandById(@PathVariable String id){
        Brand brand = this.brandService.getById(id);
        return RespResult.ok(brand);
    }

    @PostMapping("/upateBrand")
    public RespResult upateBrand(@RequestBody Brand brand){
        this.brandService.updateById(brand);
        return RespResult.ok();
    }

    @DeleteMapping("/deleteBrand/{id}")
    public RespResult deleteBrand(@PathVariable String id){
        this.brandService.removeById(id);
        return RespResult.ok();
    }

    /****
     * 条件分页查询
     */
    @PostMapping(value = "/queryPageList/{page}/{size}")
    public RespResult<Page<Brand>> queryPageList(
            @PathVariable(value = "page")Long currentPage,
            @PathVariable(value = "size")Long size,
            @RequestBody(required = false) Brand brand){
        // 分页查询
        Page<Brand> brandPage = (Page<Brand>) brandService.queryPageList(currentPage,size,brand);
        return RespResult.ok(brandPage);
    }


    /****
     * 根据分类ID查询品牌
     */
    @GetMapping(value = "/category/{id}")
    public RespResult<List<Brand>> categoryBrands(@PathVariable(value = "id")Integer id){
        List<Brand> brands = brandService.queryByCategoryId(id);
        return RespResult.ok(brands);
    }


}
