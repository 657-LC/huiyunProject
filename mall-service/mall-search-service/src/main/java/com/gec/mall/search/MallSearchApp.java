package com.gec.mall.search;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableElasticsearchRepositories(basePackages = "com.gec.mall.search.mapper")
public class MallSearchApp {
    public static void main(String[] args) {
        SpringApplication.run(MallSearchApp.class, args);
    }
}