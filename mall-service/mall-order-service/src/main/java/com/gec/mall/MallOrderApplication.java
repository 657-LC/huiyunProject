package com.gec.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(basePackages = "com.gec.mall.order.mapper")
@EnableFeignClients(basePackages = {"com.gec.mall.goods.feign","com.gec.mall.cart.feign"})
public class MallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class,args);
    }
}