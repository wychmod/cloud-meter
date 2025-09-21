package com.wychmod.controller.stress;

import com.wychmod.req.stress.StressCaseModuleDelReq;
import com.wychmod.req.stress.StressCaseModuleSaveReq;
import com.wychmod.req.stress.StressCaseModuleUpdateReq;
import com.wychmod.service.stress.StressCaseModuleService;
import com.wychmod.util.JsonData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 压测模块接口
 * 提供压测模块的增删改查接口，包括模块列表查询、模块详情查询、模块保存、模块更新和模块删除等功能
 * @author: wychmod
 * @date: 2025-09-21
 */
@RestController
@RequestMapping("api/v1/stress_case_module")
public class StressCaseModuleController {

    @Resource
    private StressCaseModuleService stressCaseModuleService;


    /**
     * 获取指定项目的压测模块列表
     *
     * @param projectId 项目ID
     * @return 压测模块列表的JsonData包装对象
     */
    @GetMapping("list")
    public JsonData list(@RequestParam("projectId")Long projectId){
        return JsonData.buildSuccess(stressCaseModuleService.list(projectId));
    }

    /**
     * 根据项目ID和模块ID查找压测模块详情
     *
     * @param projectId 项目ID
     * @param moduleId  模块ID
     * @return 压测模块详情的JsonData包装对象
     */
    @GetMapping("find")
    public JsonData findById(@RequestParam("projectId") Long projectId, @RequestParam("id") Long moduleId){
        return JsonData.buildSuccess(stressCaseModuleService.findById(projectId,moduleId));
    }


    /**
     * 删除指定的压测模块
     *
     * @param req 压测模块删除请求对象，包含项目ID和模块ID
     * @return 删除结果的JsonData包装对象
     */
    @PostMapping("/del")
    public JsonData delete(@RequestBody StressCaseModuleDelReq req){
        return JsonData.buildSuccess(stressCaseModuleService.delete(req.getProjectId(),req.getId()));
    }

    /**
     * 保存压测模块
     *
     * @param req 压测模块保存请求对象，包含项目ID和模块名称
     * @return 保存结果的JsonData包装对象
     */
    @PostMapping("/save")
    public JsonData save(@RequestBody StressCaseModuleSaveReq req){
        return JsonData.buildSuccess(stressCaseModuleService.save(req));
    }

    /**
     * 更新压测模块
     *
     * @param req 压测模块更新请求对象，包含模块ID、项目ID和模块名称
     * @return 更新结果的JsonData包装对象
     */
    @PostMapping("/update")
    public JsonData update(@RequestBody StressCaseModuleUpdateReq req){
        return JsonData.buildSuccess(stressCaseModuleService.update(req));
    }


}
