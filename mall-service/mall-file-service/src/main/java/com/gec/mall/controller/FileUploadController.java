package com.gec.mall.controller;


import com.gec.mall.service.FileUploadService;
import com.gec.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public RespResult uploadfile(MultipartFile file){
        String imageUrlPath = this.fileUploadService.uploadfile(file);
        return RespResult.ok(imageUrlPath);
    }
}
