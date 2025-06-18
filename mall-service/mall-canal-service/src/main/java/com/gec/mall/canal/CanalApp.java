package com.gec.mall.canal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient

@EnableFeignClients(basePackages = {"com.gec.mall.goods.feign","com.gec.mall.search.feign","com.gec.mall.page.feign"})
public class CanalApp {

    public static void main(String[] args) {
        SpringApplication.run(CanalApp.class, args);
    }

}