package com.gec.mall.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.mall.goods.model.SkuAttribute;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 大飞
 * @since  1.0
 */
public interface SkuAttributeMapper extends BaseMapper<SkuAttribute> {
    // 1.先从 category_attr 表 根据分类id 查询出 当前分类拥有的属性Id 集合
    //2. 再从  sku_attribute 中 查询 属性集合
    @Select("SELECT * FROM sku_attribute WHERE id IN(SELECT attr_id FROM category_attr WHERE category_id=#{id})")
    List<SkuAttribute>queryByCategoryId(Integer id);

}