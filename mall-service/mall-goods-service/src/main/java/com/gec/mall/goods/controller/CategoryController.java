package com.gec.mall.goods.controller;


import com.gec.mall.goods.model.Category;
import com.gec.mall.goods.service.CategoryService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父ID查询子类
     */
    @GetMapping(value = "/parent/{pid}")
    public RespResult<List<Category>> list(@PathVariable(value = "pid") Integer pid){
        List<Category> categories = categoryService.querByParentId(pid);
        return  RespResult.ok(categories);
    }


    /**
     * 根据id查询
     */
    @GetMapping(value = "/{id}")
    public RespResult<Category> one(@PathVariable(value = "id") Integer id){
        Category category = categoryService.getById(id);
        return RespResult.ok(category);
    }



}
