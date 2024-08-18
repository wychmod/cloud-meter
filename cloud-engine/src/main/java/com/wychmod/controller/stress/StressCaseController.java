package com.wychmod.controller.stress;


import com.wychmod.req.stress.StressCaseReq;
import com.wychmod.req.stress.StressCaseSaveReq;
import com.wychmod.req.stress.StressCaseUpdateReq;
import com.wychmod.service.stress.StressCaseService;
import com.wychmod.util.JsonFormatter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stress")
public class StressCaseController {
    @Resource
    private StressCaseService stressCaseService;

    @RequestMapping("find")
    public JsonFormatter findById(@RequestParam("projectId") Long projectId, @RequestParam("caseId") Long caseId){
        return JsonFormatter.buildSuccess(stressCaseService.findById(projectId,caseId));
    }

    @PostMapping("/del")
    public JsonFormatter delete(@RequestBody StressCaseReq stressCaseReq){
        return JsonFormatter.buildSuccess(stressCaseService.delete(stressCaseReq.getId(),stressCaseReq.getProjectId()));
    }
    @PostMapping("/save")
    public JsonFormatter save(@RequestBody StressCaseSaveReq stressCaseSaveReq){
        return JsonFormatter.buildSuccess(stressCaseService.save(stressCaseSaveReq));
    }

    @PostMapping("/update")
    public JsonFormatter update(@RequestBody StressCaseUpdateReq stressCaseUpdateReq){
        return JsonFormatter.buildSuccess(stressCaseService.update(stressCaseUpdateReq));
    }
    @GetMapping("/execute")
    public JsonFormatter execute(@RequestParam("projectId") Long projectId,@RequestParam("id") Long caseId){
        stressCaseService.execute(projectId,caseId);
        return JsonFormatter.buildSuccess();
    }
}
