package com.gec.mall.order.controller;


import com.gec.mall.order.model.Order;
import com.gec.mall.order.pay.WeixinPayParam;
import com.gec.mall.order.service.OrderService;
import com.gec.mall.util.RespCode;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private WeixinPayParam weixinPayParam;

    /***
     * 添加订单
     */
    @PostMapping
    public RespResult add(@RequestBody Order order, HttpServletRequest request) throws Exception{
        String userName = "gp";
        order.setUsername(userName);
//        order.setCreateTime(new Date());
//        order.setUpdateTime(order.getCreateTime());
//        order.setId(IdWorker.getIdStr());
//        order.setOrderStatus(0);
//        order.setPayStatus(0);
//        order.setIsDelete(0);

        //添加订单
        Boolean bo = orderService.add(order);
        String ciptxt = weixinPayParam.weixinParam(order, request);
        return bo? RespResult.ok(ciptxt) : RespResult.error(RespCode.ERROR);
    }

}
