package com.gec.mongodb.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
// 通过当前 @Document 注解中 collection 属性指定集合名称
@Document(collection = "user")
public class MyUser {

    @Id
    private ObjectId id;
    private String name;
    private Integer age;
    private String email;
    private Date createDate;
}