package com.wychmod.controller.stress;

import com.wychmod.req.stress.StressCaseModuleDelReq;
import com.wychmod.req.stress.StressCaseModuleSaveReq;
import com.wychmod.req.stress.StressCaseModuleUpdateReq;
import com.wychmod.service.stress.StressCaseModuleService;
import com.wychmod.util.JsonData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 压测模块接口
 * @author: wychmod
 * @date: 2025-09-21
 */
@RestController
@RequestMapping("api/v1/stress_case_module")
public class StressCaseModuleController {

    @Resource
    private StressCaseModuleService stressCaseModuleService;


    @GetMapping("list")
    public JsonData list(@RequestParam("projectId")Long projectId){
        return JsonData.buildSuccess(stressCaseModuleService.list(projectId));
    }

    @GetMapping("find")
    public JsonData findById(@RequestParam("projectId") Long projectId, @RequestParam("id") Long moduleId){
        return JsonData.buildSuccess(stressCaseModuleService.findById(projectId,moduleId));
    }


    @PostMapping("/del")
    public JsonData delete(@RequestBody StressCaseModuleDelReq req){
        return JsonData.buildSuccess(stressCaseModuleService.delete(req.getProjectId(),req.getId()));
    }
    @PostMapping("/save")
    public JsonData save(@RequestBody StressCaseModuleSaveReq req){
        return JsonData.buildSuccess(stressCaseModuleService.save(req));
    }

    @PostMapping("/update")
    public JsonData update(@RequestBody StressCaseModuleUpdateReq req){
        return JsonData.buildSuccess(stressCaseModuleService.update(req));
    }


}
