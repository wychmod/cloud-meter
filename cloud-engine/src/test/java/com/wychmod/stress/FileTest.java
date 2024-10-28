package com.wychmod.stress;

import com.wychmod.EngineApplication;
import com.wychmod.service.common.FileService;
import com.wychmod.util.FileUtil;
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

    @Test
    public void testUpload(){
        String readRemoteUrl = FileUtil.readRemoteUrl("http://117.72.83.9:9000/bucket/1730021495290-1cfefb7b-81fd-4710-8fbd-ff05d74ec36d-credentials.json?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minio_root%2F20241028%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20241028T170429Z&X-Amz-Expires=60&X-Amz-SignedHeaders=host&X-Amz-Signature=55b8d5f32fe2e9217b98fb5c94e0ad6b5c9110aac57a08e326f06bbbb63ad66e");
        System.out.println(readRemoteUrl);
    }
}
