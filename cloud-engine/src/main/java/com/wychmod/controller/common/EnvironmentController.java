package com.wychmod.controller.common;

import com.wychmod.req.common.EnvironmentDelReq;
import com.wychmod.req.common.EnvironmentSaveReq;
import com.wychmod.req.common.EnvironmentUpdateReq;
import com.wychmod.service.common.EnvironmentService;
import com.wychmod.util.JsonFormatter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/environment")
public class EnvironmentController {
    @Resource
    private EnvironmentService environmentService;
    @GetMapping("/list")
    public JsonFormatter list(@RequestParam("projectId") Long projectId){
        return JsonFormatter.buildSuccess(environmentService.list(projectId));
    }
    @PostMapping("/save")
    public JsonFormatter save(@RequestBody EnvironmentSaveReq environmentSaveReq){
        return JsonFormatter.buildSuccess(environmentService.save(environmentSaveReq));
    }

    @PostMapping("/update")
    public JsonFormatter save(@RequestBody EnvironmentUpdateReq environmentUpdateReq){
        return JsonFormatter.buildSuccess(environmentService.update(environmentUpdateReq));
    }

    @PostMapping("/del ")
    public JsonFormatter delete(@RequestBody EnvironmentDelReq environmentUpdateReq){
        return JsonFormatter.buildSuccess(environmentService.delete(environmentUpdateReq.getId(),environmentUpdateReq.getProjectId()));
    }
}
