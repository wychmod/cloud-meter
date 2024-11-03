package com.wychmod.service.common;

import com.wychmod.dto.common.CaseInfoDTO;
import com.wychmod.enums.TestTypeEnum;

/**
 * @description:
 * @author: wychmod
 * @date: 2024-11-03
 */
public interface ResultSendService {


    /**
     * 发送测试结果
     * @param caseInfoDTO 用例信息
     * @param testTypeEnum 测试类型
     * @param result 结果
     */
    void sendResult(CaseInfoDTO caseInfoDTO , TestTypeEnum testTypeEnum, String result);
}
