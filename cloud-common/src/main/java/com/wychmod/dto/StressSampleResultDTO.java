package com.wychmod.dto;

import lombok.Data;

/**
 * @description: 压测结果
 * @author: wychmod
 * @date: 2024-11-03
 */
@Data
public class StressSampleResultDTO {

    /**
     * 结果集id
     */
    private Long reportId;

    /**
     * 时间戳
     */
    private  Long sampleTime;

    /**
     * 采样器标签,请求名称
     */
    private String  sampleLabel;

    /**
     * 采样次数
     */
    private long sampleCount;


    /**
     * 平均响应时间
     */
    private Double  meanTime;

    /**
     * 最小响应时间
     */
    private Integer  minTime;


    /**
     * 最大响应时间
     */
    private Integer  maxTime;

    /**
     * 错误百分比
     */
    private  Double errorpercentage;

    /**
     * 错误请求数
     */
    private Long  errorCount;


    /**
     * 错误请求速率
     */
    private Double requestRate;

    /**
     * 每秒接收kb
     */
    private Double receiveKBPerSecound;

    /**
     * 每秒发送kb
     */
    private Double sendKBPerSecound;

    /**
     * 线程数量
     */
    private Integer threadCount;

    /**
     * 请求协议 主机 路径，端口 参数;
     */
    private String requestLocation;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 请求头
     */
    private String requestBody;

    /**
     *响应码
     */
    private String responseCode;

    /**
     *响应头
     */
    private String responseHeader;


    /**
     *响应体
     */
    private byte[] responseDate;

    /**
     *断言信息
     */
    private String assertInfo;
}
