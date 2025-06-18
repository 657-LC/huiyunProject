package com.gec.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.mall.order.model.Order;

public interface OrderService extends IService<Order> {
    //添加订单
    Boolean add(Order order);
}