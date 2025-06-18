package com.gec.mall.goods;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

//开启缓存注解
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.gec")
//将当前商品微服务注册到nacos上
@EnableDiscoveryClient
// 开启mp 框架中 mapper接口的包扫描
@MapperScan("com.gec.mall.goods.mapper")
public class MallGoodsApplication {
    public static void main(String[] args) {

        SpringApplication.run(MallGoodsApplication.class,args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor().setDbType(DbType.MYSQL);
    }
}