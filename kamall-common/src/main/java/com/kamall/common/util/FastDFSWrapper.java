package com.kamall.common.util;

import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author 佐斯特勒
 * <p>
 * FastDFS包装类
 * 用来包装操作FastDFSClient
 * </p>
 * @version v1.0.0
 * @date 2020/1/28 1:14
 * @see FastDFSWrapper
 **/
@Component
@Slf4j
public class FastDFSWrapper {
    @Resource
    private FastFileStorageClient fastFileStorageClient;
    @Resource
    private ThumbImageConfig thumbImageConfig;

    /**
     * 文件上传
     * 最后返回fastDFS中的文件名称; group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     *
     * @param bytes     文件字节
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return fastDfs路径
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        var byteArrayInputStream = new ByteArrayInputStream(bytes);
        var storePath = fastFileStorageClient.uploadFile(byteArrayInputStream, fileSize, extension, null);
        log.info(storePath.getGroup() + "==" + storePath.getPath() + "======" + storePath.getFullPath());
        return storePath.getFullPath();
    }

    /**
     * 文件上传 原图+150x150缩略图
     *
     * @param bytes     文件字节
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return fastDfs路径
     */
    public String[] uploadThumbFile(byte[] bytes, long fileSize, String extension) {
        var byteArrayInputStream = new ByteArrayInputStream(bytes);
        var storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(byteArrayInputStream, fileSize, extension, null);
        log.info(storePath.getGroup() + "==" + thumbImageConfig.getThumbImagePath(storePath.getPath()) + "======" + storePath.getFullPath());
        return new String[]{thumbImageConfig.getThumbImagePath(storePath.getFullPath()), storePath.getFullPath()};
    }

    /**
     * 下载文件
     * 返回文件字节流大小
     *
     * @param fileUrl 文件URL
     * @return 文件字节
     * @throws IOException .
     */
    public byte[] downloadFile(String fileUrl) throws IOException {
        fileUrl = fileUrl.substring(fileUrl.indexOf("group"));
        var group = fileUrl.substring(0, fileUrl.indexOf("/"));
        var path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        var downloadByteArray = new DownloadByteArray();
        log.info("group:" + group + "--path:" + path);
        return fastFileStorageClient.downloadFile(group, path, downloadByteArray);
    }

    /**
     * 删除图片
     *
     * @param fileUrl 文件地址
     */
    public void delFile(String fileUrl) {
        fastFileStorageClient.deleteFile(fileUrl);
    }
}

