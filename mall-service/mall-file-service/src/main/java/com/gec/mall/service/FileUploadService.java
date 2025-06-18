package com.gec.mall.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadfile(MultipartFile file);
}
