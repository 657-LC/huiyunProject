package com.gec;

import io.minio.*;

public class MinioTestDemo {

    public static void main(String[] args) {

        try {
            //构造MinIO Client
            MinioClient minioClient = MinioClient.builder()

                    // minio 服务器的url 地址
                    .endpoint("http://192.168.31.128:9000")

                    // minio 服务器需要的 账号 和密码

                    .credentials("admin", "admin123456")
                    .build();

            //创建hello-minio桶
                //  1. 判断当前bucket 桶是否存在
                  //   如果这个桶已经存在，就直接使用
            //    如果 这个桶不存在，就需要去创建

            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("hello-minio").build());
            if (!found) {
                // 当前 hello-minio 这个桶是不存在 所以我们需要去创建
                //创建hello-minio桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("hello-minio").build());

                // 创建出来的 minio的桶 默认权限是 private

                //设置hello-minio桶的访问权限
                String policy = """
                        {
                          "Statement" : [ {
                            "Action" : "s3:GetObject",
                            "Effect" : "Allow",
                            "Principal" : "*",
                            "Resource" : "arn:aws:s3:::hello-minio/*"
                          } ],
                          "Version" : "2012-10-17"
                        }""";
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket("hello-minio").config(policy).build());
            } else {

                // 表示桶已经存在了 ！！
                System.out.println("Bucket 'hello-minio' already exists.");
            }

            //上传图片
            minioClient.uploadObject(
                    UploadObjectArgs.builder()

                            // 将 你本地资源上传到 哪个 桶中
                            .bucket("hello-minio")

                            // 你上传到 minio服务器上的资源名称
                            .object("公寓-外观.jpg")

                            // 你本地资源的 文件路径和名称
                            .filename("D:\\images\\1.jpg")
                            .build());
            System.out.println("上传成功");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }

    }

}
