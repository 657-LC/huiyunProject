package com.gec.mall.page;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.gec.mall.goods.feign"})
public class MallPageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallPageApplication.class,args);
    }
}
