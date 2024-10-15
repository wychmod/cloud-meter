package com.wychmod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 测试报告数据传输对象
 * @author: wychmod
 * @date: 2024-10-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO  {

    private Long id;

    private Long projectId;

    private Long caseId;

    private String type;

    private String name;

    private String executeState;

    private String summary;

    private Long startTime;

    private Long endTime;

    private Long expandTime;

    private Long quantity;

    private Long passQuantity;

    private Long failQuantity;

    private Date gmtCreate;

    private Date gmtModified;
}
