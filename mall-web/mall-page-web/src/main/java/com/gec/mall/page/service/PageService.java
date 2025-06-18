package com.gec.mall.page.service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface PageService {

    //生成静态页
    void html(String id) throws FileNotFoundException, UnsupportedEncodingException;

}
