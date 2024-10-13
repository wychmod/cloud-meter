package com.wychmod.controller.common;


import com.wychmod.req.common.ProjectDelReq;
import com.wychmod.req.common.ProjectSaveReq;
import com.wychmod.req.common.ProjectUpdateReq;
import com.wychmod.service.common.ProjectService;
import com.wychmod.util.JsonFormatter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    /**
     * 获取项目列表
     *
     * @return 包含项目列表的Json格式化对象
     */
    @GetMapping ("/list")
    public JsonFormatter list() {
        return JsonFormatter.buildSuccess(projectService.list());
    }

    /**
     * 保存新项目
     *
     * @param req 包含项目信息的请求体
     * @return 包含保存结果的Json格式化对象
     */
    @PostMapping ("/save")
    public JsonFormatter save(@RequestBody ProjectSaveReq req) {
        return JsonFormatter.buildSuccess(projectService.save(req));
    }

    /**
     * 更新现有项目
     *
     * @param req 包含更新信息的请求体
     * @return 包含更新结果的Json格式化对象
     */
    @PostMapping ("/update")
    public JsonFormatter update(@RequestBody ProjectUpdateReq req) {
        return JsonFormatter.buildSuccess(projectService.update(req));
    }

    /**
     * 删除指定项目
     *
     * @param req 包含项目ID的请求体
     * @return 包含删除结果的Json格式化对象
     */
    @DeleteMapping ("/del")
    public JsonFormatter delete(@RequestBody ProjectDelReq req) {
        return JsonFormatter.buildSuccess(projectService.delete(req.getId()));
    }
}
