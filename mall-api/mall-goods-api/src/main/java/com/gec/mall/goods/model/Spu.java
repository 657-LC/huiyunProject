package com.gec.mall.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName(value = "spu")
public class Spu {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String intro;
    private Integer brandId;
    private Integer categoryOneId;
    private Integer categoryTwoId;
    private Integer categoryThreeId;
    private String images;
    private String afterSalesService;
    private String content;
    private String attributeList;
    private Integer isMarketable;
    private Integer isDelete;
    private Integer status;
}
