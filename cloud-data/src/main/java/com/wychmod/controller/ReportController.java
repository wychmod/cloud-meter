package com.wychmod.controller;

import com.wychmod.dto.ReportDTO;
import com.wychmod.req.ReportSaveReq;
import com.wychmod.service.ReportService;
import com.wychmod.util.JsonFormatter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: wychmod
 * @date: 2024-10-14
 */
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Resource
    private ReportService reportService;

    @PostMapping("/save")
    public JsonFormatter save(@RequestBody ReportSaveReq req){
        ReportDTO reportDTO = reportService.save(req);
        return  JsonFormatter.buildSuccess(reportDTO);
    }
}
