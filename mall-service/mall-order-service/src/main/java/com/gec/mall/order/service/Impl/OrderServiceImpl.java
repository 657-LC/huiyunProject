package com.gec.mall.order.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.mall.cart.feign.CartFeign;
import com.gec.mall.cart.model.Cart;
import com.gec.mall.goods.feign.SkuFeign;
import com.gec.mall.order.mapper.OrderMapper;
import com.gec.mall.order.mapper.OrderSkuMapper;
import com.gec.mall.order.model.Order;
import com.gec.mall.order.model.OrderSku;
import com.gec.mall.order.service.OrderService;
import com.gec.mall.util.RespResult;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private OrderSkuMapper orderSkuMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SkuFeign skuFeign;
    @Autowired
    private CartFeign cartFeign;


    /**
     * 添加订单
     * @param order
     * @return
     */
    @GlobalTransactional
    @Override
    public Boolean add(Order order) {
        //1.查询购物车记录
        RespResult<List<Cart>> cartResp = cartFeign.list(order.getCartIds());
        List<Cart> carts = IterableConverter.toList(cartResp.getData());
        if (carts.size()==0){
            return false;
        }

        //2.库存递减
        skuFeign.decount(carts);

        //3.添加订单明细
        int totlum = 0;//商品个数
        int paymMoney = 0;//支付总金额

        for (Cart cart : carts){
            //类型转换
            OrderSku orderSku = JSON.parseObject(JSON.toJSONString(cart), OrderSku.class);
            orderSku.setId(IdWorker.getIdStr());
            orderSku.setMoney(orderSku.getPrice()*orderSku.getNum());
            orderSku.setSkuId(cart.getSkuId());
            orderSku.setOrderId(order.getId());
            orderSkuMapper.insert(orderSku);

            //统计数据
            totlum += cart.getNum();
            paymMoney += orderSku.getMoney();
        }

        //4.添加订单
        order.setTotalNum(totlum);
        order.setMoneys(paymMoney);
        orderMapper.insert(order);

//        //.模拟异常
//        int i = 1/0;

        //5.删除记录
        cartFeign.delete(order.getCartIds());
        return true;
    }
}





















