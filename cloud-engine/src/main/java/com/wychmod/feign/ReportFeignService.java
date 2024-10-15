package com.wychmod.feign;

import com.wychmod.req.ReportSaveReq;
import com.wychmod.util.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: data的Feign接口
 * @author: wychmod
 * @date: 2024-10-16
 */
@FeignClient(name = "data-service")
public interface ReportFeignService {

    /**
     * 初始化测试报告接口;
     */
    @PostMapping("/api/v1/report/save")
    public JsonData save(@RequestBody ReportSaveReq req);

}
