package com.wychmod.service.common.impl;

import com.wychmod.config.MinioConfig;
import com.wychmod.service.common.FileService;
import com.wychmod.util.FileUtil;
import io.minio.MinioClient;
import io.minio.MinioProperties;
import io.minio.PutObjectArgs;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @description: 文件服务实现类
 * @author: wychmod
 * @date: 2024-10-26
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {


    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioConfig minioConfig;

    /**
     * 1. 生成文件名称
     * 2. 拼接路径返回
     */
    @Override
    public String upload(MultipartFile file) {
        String fileName = FileUtil.getFileName(file.getOriginalFilename());
        new Thread(() -> {
            upload(file, fileName);
        });

        return minioConfig.getEndpoint()+"/"+ minioConfig.getBucketName()+"/"+fileName;
    }


    /**
     * 将multipart文件上传到MinIO服务器
     * @param file 要上传的multipart文件，不能为null且大小不能为0
     * @param fileName 文件名，用于在桶中标识文件
     */
    private void upload(MultipartFile file, String fileName) {
        // 检查文件是否为空，如果为空，则抛出异常
        if (file == null || file.getSize() == 0) {
            throw new RuntimeException("文件为空");
        }else{
            try{
                // 获取文件输入流
                InputStream inputStream = file.getInputStream();
                // 构建上传对象的参数，包括桶名、对象名、文件流和文件大小
                PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(minioConfig.getBucketName()).object(fileName)
                        .stream(inputStream, file.getSize(), -1).build();
                // 使用MinIO客户端上传文件
                minioClient.putObject(putObjectArgs);
            }catch (Exception e){
                // 如果上传过程中发生异常，抛出运行时异常
                throw new RuntimeException("文件上传失败");
            }
        }
    }

    @Override
    public String getTempAccessFileUrl(String remoteFilePath) {
        return null;
    }

    @Override
    public String copyRemoteFileTotalTemoplate(String remoteFilePath) {
        return null;
    }
}
