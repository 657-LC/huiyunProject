package com.gec.mall.util;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

    public static void main(String[] args) throws Exception {
        String str = "12345";
        System.out.println("通过MD5加密后的密文是："+MD5.md5(str));

    }


    /**
     * MD5方法
     */
    public static String md5(String text) throws Exception{
        //加密后的字符串
        String encode = DigestUtils.md5Hex(text);
        return encode;
    }


    /**
     * MD5验证方法
     */
    public static boolean verify(String text,String key,String md5) throws Exception{
        //根据传入的密钥进行验证
        String md5Text = md5(text,key);
        return md5Text.equalsIgnoreCase(md5);
    }

    /**
     * m5方法
     */
    public static String md5(String text,String slat) throws Exception{
        String encode = DigestUtils.md5Hex(text+slat);
        return encode;
    }
}
