package com.gec.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


//  通过 springboot 注解 ConfigurationProperties  根据 springboot 中配置文件 minio前缀 来读取相关配置属性
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;
    
    private String bucketName;
}