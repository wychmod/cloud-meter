package com.wychmod.service.Impl;

import com.wychmod.dto.ReportDTO;
import com.wychmod.model.ReportDO;
import com.wychmod.req.ReportSaveReq;
import com.wychmod.service.ReportService;
import com.wychmod.util.SpringBeanUtil;
import com.wychmod.mapper.ReportMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: wychmod
 * @date: 2024-10-14
 */
@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportMapper reportMapper;

    @Override
    public ReportDTO save(ReportSaveReq req) {
        ReportDO reportDO = SpringBeanUtil.copyProperties(req, ReportDO.class);
        reportMapper.insert(reportDO);
        ReportDTO reportDTO = ReportDTO.builder().id(reportDO.getId()).projectId(reportDO.getProjectId()).name(reportDO.getName()).build();
        return reportDTO;
    }
}
