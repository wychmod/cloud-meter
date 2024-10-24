package com.wychmod.controller.common;

import com.wychmod.util.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 文件上传接口
 * @author: wychmod
 * @date: 2024-10-25
 */
@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    /**
     * 文件上传接口
     */
    @PostMapping("/upload")
    public JsonData upload(@RequestParam("file") MultipartFile file){
        return JsonData.buildSuccess();
    }
}
