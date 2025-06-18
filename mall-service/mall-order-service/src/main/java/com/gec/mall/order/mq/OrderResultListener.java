package com.gec.mall.order.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Component
@RocketMQMessageListener(topic = "log", consumerGroup = "order-group")
public class OrderResultListener implements RocketMQListener, RocketMQPushConsumerLifecycleListener {
    /***
     * 监听消息
     * 实现RocketMQPushConsumerLifecycleListener监听器之后，此方法不调用
     * @param message
     */
    @Override
    public void onMessage(Object message) {
    }


    /**
     * 消息监听
     * @param defaultMQPushConsumer
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                try {
                    for (MessageExt msg : list) {
                        String result = new String(msg.getBody(),"UTF-8");
                        System.out.println("result===========================:"+result);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //消费状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}