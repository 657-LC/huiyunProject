package com.gec.mall.pay.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.mall.pay.mapper.PayLogMapper;
import com.gec.mall.pay.model.PayLog;
import com.gec.mall.pay.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private PayLogMapper payLogMapper;

    /***
     * 记录日志
     * @param payLog
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void log(PayLog payLog) {
        //本地操作
        int count = payLogMapper.insert(payLog);
    }
}