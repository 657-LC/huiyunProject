package com.gec.mall.config;
import com.gec.mall.util.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author  dafei
 * @since 1.0
 */
@Configuration
public class SecurityConfig {
    @Value("${payconfig.aes.skey}")
    private String skey;

    @Value("${payconfig.aes.salt}")
    private String salt;

    /*****
     * 加密解密工具类
     * @return
     */
    @Bean
    public Signature signature(){
        return new Signature(skey,salt);
    }
}