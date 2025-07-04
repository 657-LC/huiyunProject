package com.gec.mall.order.pay;
import com.gec.mall.order.model.Order;
import com.gec.mall.util.IPUtils;
import com.gec.mall.util.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
// 提供一个工具类对支付的数据进行加密
@Component
public class WeixinPayParam {
    @Autowired
    private Signature signature;
    /*****
     * 支付数据处理
     */
    public String weixinParam(Order order, HttpServletRequest request) throws Exception {
        //预支付下单需要用到的数据
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "SpringCloud Alibaba商城");
        data.put("out_trade_no", order.getId());    //订单号
        data.put("device_info", "PC");  // 设备
        data.put("fee_type", "CNY");    // 币种
        //data.put("total_fee", String.valueOf(order.getMoneys()*100));     //支付金额
        data.put("total_fee","1");     //支付金额
        data.put("spbill_create_ip", IPUtils.getIpAddr(request));  //客户端IP
        data.put("notify_url", "http://2cw4969042.wicp.vip:48847/wx/result");  //回调地址（支付结果通知地址）
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付

        // TrreMap->MD5->Map->JSON->AES
        return signature.security(data);
    }
}