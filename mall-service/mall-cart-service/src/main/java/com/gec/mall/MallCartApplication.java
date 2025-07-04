package com.gec.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = "com.gec.mall.goods.feign")
public class MallCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCartApplication.class,args);
    }
}