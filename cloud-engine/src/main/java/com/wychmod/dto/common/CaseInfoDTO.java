package com.wychmod.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 测试用例信息
 * @author: wychmod
 * @date: 2024-11-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseInfoDTO {

    /**
     * 用例id
     */
    private Long Id;

    /**
     * 所属模块的ID
     */
    private Long moduleId;

    /**
     * 用例名称
     */
    private String name;
}
