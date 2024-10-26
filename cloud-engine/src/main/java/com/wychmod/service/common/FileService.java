package com.wychmod.service.common;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 文件上传服务
 * @author: wychmod
 * @date: 2024-10-26
 */
public interface FileService {


    /**
     * 上传文件
     */
    String upload(MultipartFile file);

    /**
     * 获取对象的临时文件，有效期为1min;也可以当参数传入;
     */
    String getTempAccessFileUrl(String remoteFilePath) ;

    /**
     * 读取远程文件下载到本地创建临时文件
     */
    String copyRemoteFileTotalTemoplate(String remoteFilePath);
}
