package com.wychmod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ReportDTO类用于定义报告的数据传输对象
 * 该类通过注解简化了getter和setter方法的生成，以及对象的创建和复制
 * 主要用于在不同层之间传输报告相关数据，如报告的基本信息、执行状态、时间等
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    // 报告的唯一标识
    private Long id;

    // 项目ID，用于关联报告所属的项目
    private Long projectId;

    // 用例ID，报告中涉及的测试用例
    private Long caseId;

    // 报告类型，如回归测试、冒烟测试等
    private String type;

    // 报告名称
    private String name;

    // 执行状态，表示报告中测试的执行情况，如已执行、未执行、执行中等
    private String executeState;

    // 总结，报告的总体概述
    private String summary;

    // 开始时间戳，记录报告开始执行的时间
    private Long startTime;

    // 结束时间戳，记录报告执行完成的时间
    private Long endTime;

    // 扩展时间，用于记录报告执行过程中额外的时间消耗
    private Long expandTime;

    // 数量，报告中包含的测试项总数
    private Long quantity;

    // 通过数量，报告中通过的测试项数量
    private Long passQuantity;

    // 失败数量，报告中失败的测试项数量
    private Long failQuantity;

    // 创建时间，记录报告对象创建的时间
    private Date gmtCreate;

    // 修改时间，记录报告对象最后修改的时间
    private Date gmtModified;
}
