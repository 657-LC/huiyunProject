package com.gec.mall.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.mall.goods.mapper.SkuAttributeMapper;
import com.gec.mall.goods.model.SkuAttribute;
import com.gec.mall.goods.service.SkuAttributeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuAttributeServiceImpl extends ServiceImpl<SkuAttributeMapper, SkuAttribute> implements SkuAttributeService {
    @Override
    public List<SkuAttribute> queryList(Integer id) {
        return this.baseMapper.queryByCategoryId(id);
    }
}
