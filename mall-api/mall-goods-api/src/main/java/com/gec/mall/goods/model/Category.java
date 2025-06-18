package com.gec.mall.goods.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("category")
public class Category implements Serializable {
    private Integer id;
    private String name;
    private Integer sort;
    private Integer parent_id;

}
