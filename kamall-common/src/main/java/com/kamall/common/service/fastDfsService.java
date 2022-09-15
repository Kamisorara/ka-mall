package com.kamall.common.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * fastDfs上传文件Service
 */
public interface fastDfsService {
    //上传文件
    String uploadImg(MultipartFile multipartFile) throws IOException;
}
