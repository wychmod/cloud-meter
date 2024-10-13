package com.wychmod.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 报告保存请求类
 * 该类用于封装测试报告保存请求的相关信息，包括项目ID、用例ID、报告类型、报告名称和执行状态等
 * 主要用于向服务器发送报告保存请求时，携带报告的相关元数据
 *
 * @author: wychmod
 * @date: 2024-10-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportSaveReq {

    // 项目ID，用于标识报告所属的项目
    private Long projectId;

    // 用例ID，用于关联报告和特定测试用例
    private Long caseId;

    // 报告类型，用于区分不同类型的报告，例如压测报告、UI测试报告等
    private String type;

    // 报告名称
    private String name;

    // 执行状态，用于标识报告中测试用例的执行结果，如成功、失败或未执行等
    private String executeState;

    // 开始时间，用于记录报告开始执行的时间戳，便于后续分析和排序
    private Long startTime;
}
