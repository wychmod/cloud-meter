package com.wychmod.controller.common;

import com.wychmod.req.common.EnvironmentDelReq;
import com.wychmod.req.common.EnvironmentSaveReq;
import com.wychmod.req.common.EnvironmentUpdateReq;
import com.wychmod.service.common.EnvironmentService;
import com.wychmod.util.JsonFormatter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 环境控制器类，用于处理与环境相关的HTTP请求
 */
@RestController
@RequestMapping("api/v1/environment")
public class EnvironmentController {
    /**
     * 环境服务接口的实例，用于操作环境数据
     */
    @Resource
    private EnvironmentService environmentService;

    /**
     * 获取项目环境列表
     * @param projectId 项目ID，用于筛选特定项目的环境
     * @return 返回包含环境列表的JsonFormatter对象
     */
    @GetMapping("/list")
    public JsonFormatter list(@RequestParam("projectId") Long projectId){
        return JsonFormatter.buildSuccess(environmentService.list(projectId));
    }

    /**
     * 保存新的环境配置
     * @param environmentSaveReq 包含新环境配置的请求对象
     * @return 返回表示操作成功的JsonFormatter对象
     */
    @PostMapping("/save")
    public JsonFormatter save(@RequestBody EnvironmentSaveReq environmentSaveReq){
        return JsonFormatter.buildSuccess(environmentService.save(environmentSaveReq));
    }

    /**
     * 更新现有环境配置
     * @param environmentUpdateReq 包含更新环境配置的请求对象
     * @return 返回表示操作成功的JsonFormatter对象
     */
    @PostMapping("/update")
    public JsonFormatter save(@RequestBody EnvironmentUpdateReq environmentUpdateReq){
        return JsonFormatter.buildSuccess(environmentService.update(environmentUpdateReq));
    }

    /**
     * 删除指定的环境
     * @param environmentDelReq 包含要删除的环境信息的请求对象
     * @return 返回表示操作成功的JsonFormatter对象
     */
    @PostMapping("/del ")
    public JsonFormatter delete(@RequestBody EnvironmentDelReq environmentDelReq){
        return JsonFormatter.buildSuccess(environmentService.delete(environmentDelReq.getId(),environmentDelReq.getProjectId()));
    }
}
