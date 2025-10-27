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
        String readRemoteUrl = FileUtil.readRemoteUrl("http://117.72.218.181:9000/bucket/login_form.jmx?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=SEKK4MSN9YBKBJC6IY1I%2F20251027%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20251027T170653Z&X-Amz-Expires=604800&X-Amz-Security-Token=eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhY2Nlc3NLZXkiOiJTRUtLNE1TTjlZQktCSkM2SVkxSSIsImV4cCI6MTc2MTYyNzk3NiwicGFyZW50IjoibWluaW9fcm9vdCJ9.GMZdO67gVJF5TkbZ9FPy_20YXXJDcf1bRhV_r-5ObgR6_v_89ENDtc9dyjBFMeI27BGxQsMsIQqU98PBBLBD4w&X-Amz-SignedHeaders=host&versionId=null&X-Amz-Signature=a969d8ba118915fb118127d63aaba2dc152c32a1480c50e44d02b5221328a731");
        System.out.println(readRemoteUrl);
    }
}
