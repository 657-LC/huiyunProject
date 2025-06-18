package com.gec.mall.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinoConfig {
    @Autowired
    private MinioProperties minioProperties;
    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient = MinioClient.builder()
                //minio服务的url
                .endpoint(minioProperties.getEndpoint())
                //minio服务的账号和密码
                .credentials(minioProperties.getAccessKey(),minioProperties.getSecretKey())
                .build();
        return minioClient;
    }
}
