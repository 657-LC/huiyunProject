package com.gec.mall.pay.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gec.mall.pay.model.PayLog;
import com.gec.mall.pay.service.PayLogService;
import com.gec.mall.util.RespResult;
import com.gec.mall.util.Signature;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/wx")
public class WeixinPayController {
    @Autowired
    private PayLogService payLogService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private Signature signature;

    /**
     * 记录支付结果
     * 执行事务消息发送
     */
    @GetMapping(value = "result")
    public RespResult payLog() {
        //记录日志
        PayLog payLog = new PayLog(IdWorker.getIdStr(), 1, "test", "No001", new Date());
        Message message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();
        rocketMQTemplate.sendMessageInTransaction("rocket", "log", message, null);
        return RespResult.ok();
    }


//    @GetMapping(value = "/pay")
//    //public RespResult<Map> pay(@RequestParam Map<String,String> dataMap) throws Exception {
//    public RespResult<Map> pay(@RequestParam("ciptext") String ciphertext) throws Exception {
//        //ciphertext->AES->移除签名数据signature->MD5==signature?
//        Map<String, String> dataMap = signature.security(ciphertext);
//
//        Map<String, String> map = weixinPayService.preOrder(dataMap);
//        if (map != null) {
//            map.put("orderNumber", dataMap.get("out_trade_no"));
//            map.put("money", dataMap.get("total_fee"));
//            return RespResult.ok(map);
//        }
//        return RespResult.error("支付系统繁忙，请稍后再试！");
//    }

}