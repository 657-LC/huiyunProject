package com.gec.mall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.mall.pay.model.PayLog;

public interface PayLogService extends IService<PayLog> {
    void log(PayLog payLog);
}