package com.kamall.common.service.impl;

import com.kamall.common.service.fastDfsService;
import com.kamall.common.util.FastDFSWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class fastDfsServiceImpl implements fastDfsService {
    @Autowired
    private FastDFSWrapper fastDFSWrapper;

    /**
     * 上传文件
     */
    @Override
    public String uploadImg(MultipartFile multipartFile) throws IOException {
        //getByte
        byte[] fileBytes = multipartFile.getBytes();
        //获取扩展名
        String originName = multipartFile.getOriginalFilename();
        //获取文件后缀
        String suffix = originName.substring(originName.lastIndexOf("."));
        //获取大小
        long size = multipartFile.getSize();
        String urlTail = fastDFSWrapper.uploadFile(fileBytes, size, suffix);
        return "http://img.kamisora.xyz:8080/" + urlTail;
    }
}
