package com.gec.mall.service.impl;


import com.gec.mall.config.MinioProperties;
import com.gec.mall.service.FileUploadService;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioProperties minioProperties;



    @Override
    public String uploadfile(MultipartFile file) {


        try {

            //1. 判断当前bucket 桶是否存在
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!bucketExists) {
                // 当前桶不存在，所以需要创建
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());

                // 给当前桶设置访问权限
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(minioProperties.getBucketName()).config(createBucketPolicyConfig(minioProperties.getBucketName())).build());
                // minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(minioProperties.getBucketName()).build());
            }

            // 对上传的文件名进行处理    (文件存放路径  年月日 父目录/1278187871721721.jpg)
            String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

            // 实现上传操作

            minioClient.putObject(PutObjectArgs.builder().
                    bucket(minioProperties.getBucketName()).
                    object(filename).
                    stream(file.getInputStream(), file.getSize(), -1).
                    contentType(file.getContentType()).build());

            // http://192.168.137.128:9000/test-01/goods01.png

            return String.join("/", minioProperties.getEndpoint(), minioProperties.getBucketName(), filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createBucketPolicyConfig(String bucketName) {

        return """
            {
              "Statement" : [ {
                "Action" : "s3:GetObject",
                "Effect" : "Allow",
                "Principal" : "*",
                "Resource" : "arn:aws:s3:::%s/*"
              } ],
              "Version" : "2012-10-17"
            }
            """.formatted(bucketName);
    }

}
