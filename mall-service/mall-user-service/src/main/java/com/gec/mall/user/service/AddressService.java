package com.gec.mall.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.mall.user.model.Address;

import java.util.List;

public interface AddressService extends IService<Address> {
    //根据用户名查询地址列表集合
    List<Address> list(String userName);
}