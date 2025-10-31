package com.wychmod.service.common.impl;

import com.wychmod.dto.common.CaseInfoDTO;
import com.wychmod.enums.TestTypeEnum;
import com.wychmod.service.common.ResultSenderService;

/**
 * @description: kafka消息发送实现类
 * @author: wychmod
 * @date: 2024-11-03
 */
public class KafkaSenderServiceImpl implements ResultSenderService {
    @Override
    public void sendResult(CaseInfoDTO caseInfoDTO, TestTypeEnum testTypeEnum, String result) {

    }
}
