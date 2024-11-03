package com.wychmod.enums;

/**
 * TestTypeEnum定义了测试类型的枚举，用于标准化测试类型的选择
 *
 * @author: wychmod
 * @date: 2024-11-03
 */
public enum TestTypeEnum {

    /**
     * STRESS代表压力测试类型，用于系统在高负载下的性能和稳定性测试
     */
    STRESS,

    /**
     * API代表应用程序接口测试类型，用于验证系统接口的功能是否正确
     */
    API,

    /**
     * UI代表用户界面测试类型，用于检查系统的用户界面是否按预期工作
     */
    UI
}
