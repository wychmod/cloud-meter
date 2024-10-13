package com.wychmod.controller.stress;


import com.wychmod.req.stress.StressCaseReq;
import com.wychmod.req.stress.StressCaseSaveReq;
import com.wychmod.req.stress.StressCaseUpdateReq;
import com.wychmod.service.stress.StressCaseService;
import com.wychmod.util.JsonFormatter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器类，用于处理与性能测试用例相关的HTTP请求
 */
@RestController
@RequestMapping("api/v1/stress")
public class StressCaseController {
    /**
     * 注入性能测试用例服务类
     */
    @Resource
    private StressCaseService stressCaseService;

    /**
     * 根据项目ID和用例ID查询性能测试用例
     *
     * @param projectId 项目ID
     * @param caseId 用例ID
     * @return 返回查询到的用例信息
     */
    @RequestMapping("find")
    public JsonFormatter findById(@RequestParam("projectId") Long projectId, @RequestParam("caseId") Long caseId){
        return JsonFormatter.buildSuccess(stressCaseService.findById(projectId,caseId));
    }

    /**
     * 删除性能测试用例
     *
     * @param stressCaseReq 包含要删除的用例信息的请求对象
     * @return 返回删除成功的信息
     */
    @PostMapping("/del")
    public JsonFormatter delete(@RequestBody StressCaseReq stressCaseReq){
        return JsonFormatter.buildSuccess(stressCaseService.delete(stressCaseReq.getId(),stressCaseReq.getProjectId()));
    }

    /**
     * 保存性能测试用例
     *
     * @param stressCaseSaveReq 包含要保存的用例信息的请求对象
     * @return 返回保存成功的信息
     */
    @PostMapping("/save")
    public JsonFormatter save(@RequestBody StressCaseSaveReq stressCaseSaveReq){
        return JsonFormatter.buildSuccess(stressCaseService.save(stressCaseSaveReq));
    }

    /**
     * 更新性能测试用例
     *
     * @param stressCaseUpdateReq 包含要更新的用例信息的请求对象
     * @return 返回更新成功的信息
     */
    @PostMapping("/update")
    public JsonFormatter update(@RequestBody StressCaseUpdateReq stressCaseUpdateReq){
        return JsonFormatter.buildSuccess(stressCaseService.update(stressCaseUpdateReq));
    }

    /**
     * 执行性能测试用例
     *
     * @param projectId 项目ID
     * @param caseId 用例ID
     * @return 返回执行成功的信息
     */
    @GetMapping("/execute")
    public JsonFormatter execute(@RequestParam("projectId") Long projectId,@RequestParam("id") Long caseId){
        stressCaseService.execute(projectId,caseId);
        return JsonFormatter.buildSuccess();
    }
}
