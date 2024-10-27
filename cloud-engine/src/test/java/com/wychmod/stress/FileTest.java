package com.wychmod.stress;

import com.wychmod.EngineApplication;
import com.wychmod.service.common.FileService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: 文件测试类
 * @author: wychmod
 * @date: 2024-10-27
 */
@SpringBootTest(classes = EngineApplication.class)
@RunWith(SpringRunner.class)
public class FileTest {

    @Resource
    private FileService fileService;

    @Test
    public void testTempFileApi(){
        String tempAccessFileUrl = fileService.getTempAccessFileUrl
                ("http://117.72.83.9:9000/bucket/1730021495290-1cfefb7b-81fd-4710-8fbd-ff05d74ec36d-credentials.json");
        System.out.println(tempAccessFileUrl);
    }
}
