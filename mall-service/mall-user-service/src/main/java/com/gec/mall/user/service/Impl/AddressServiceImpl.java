package com.gec.mall.user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.mall.user.mapper.AddressMapper;
import com.gec.mall.user.model.Address;
import com.gec.mall.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /***
     * 根据用户名查询地址列表集合
     * @param userName
     * @return
     */
    @Override
    public List<Address> list(String userName) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<Address>();
        queryWrapper.eq("username",userName);
        return addressMapper.selectList(queryWrapper);
    }
}