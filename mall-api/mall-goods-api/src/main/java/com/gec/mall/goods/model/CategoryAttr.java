package com.gec.mall.goods.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 大飞
 * @since 1.0
 ****/
@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus表映射注解
@TableName(value = "category_attr")
public class CategoryAttr {

    @TableField
    private Integer categoryId;

    @TableField
    private Integer attrId;

}
