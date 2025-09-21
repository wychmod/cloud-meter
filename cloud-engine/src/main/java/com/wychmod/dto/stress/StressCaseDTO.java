package com.wychmod.dto.stress;

import lombok.Data;

import java.util.Date;

/**
 * 压测用例数据传输对象
 * 用于封装压测用例的相关信息，包括基础信息、请求配置、断言配置等
 */
@Data
public class StressCaseDTO {


    /**
     * 用例唯一标识符
     */
    private Long id;

    /**
     * 所属项目ID
     */
    private Long projectId;

    /**
     * 所属模块ID
     */
    private Long moduleId;


    /**
     * 环境ID
     */
    private Long enviromentId;


    /**
     * 用例名称
     */
    private String name;


    /**
     * 用例描述
     */
    private String description;

    /**
     * 断言配置
     */
    private String assertion;


    /**
     * 关联关系配置
     */
    private String relation;


    /**
     * 线程组配置
     */
    private String threadGroupConfig;


    /**
     * JMX文件URL
     */
    private String jmxUrl;

    /**
     * 请求路径
     */
    private String path;


    /**
     * 请求方法
     */
    private String method;

    /**
     * 查询参数
     */
    private String query;

    /**
     * 请求头
     */
    private String header;

    /**
     * 请求体
     */
    private String body;

    /**
     * 请求体类型
     */
    private String bodyType;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    private Date gmtModified;
}
