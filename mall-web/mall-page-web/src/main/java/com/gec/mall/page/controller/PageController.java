package com.gec.mall.page.controller;


import com.gec.mall.page.service.PageService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/page")
public class PageController {
    @Autowired
    private PageService pageService;


    //生成静态页
    @GetMapping(value = "/{id}")
    public RespResult html(@PathVariable(value = "id")String id) throws Exception{
        pageService.html(id);
        return RespResult.ok();
    }





}
