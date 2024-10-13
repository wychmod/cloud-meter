package com.wychmod.service;

import com.wychmod.dto.ReportDTO;
import com.wychmod.req.ReportSaveReq;

/**
 * @description: 测试报告服务接口
 * @author: wychmod
 * @date: 2024-10-14
 */
public interface ReportService {

    /**
     * 保存测试报告，这个是公共用的，用例和压测模块等公用的报告;
     */
    ReportDTO save(ReportSaveReq req);
}
