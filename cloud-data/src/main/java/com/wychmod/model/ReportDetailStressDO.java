package com.wychmod.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wychmod
 * @since 2024-08-14
 */
@Getter
@Setter
@TableName("report_detail_stress")
@Schema(name = "ReportDetailStressDO", description = "")
public class ReportDetailStressDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "所属报告ID")
    @TableField("report_id")
    private Long reportId;

    @Schema(description = "断信信息")
    @TableField("assert_info")
    private String assertInfo;

    @Schema(description = "错误请求数")
    @TableField("error_count")
    private Long errorCount;

    @Schema(description = "错误百分比")
    @TableField("error_percentage")
    private Double errorPercentage;

    @Schema(description = "最大响应时间")
    @TableField("max_time")
    private Integer maxTime;

    @Schema(description = "平均响应时间")
    @TableField("mean_time")
    private Double meanTime;

    @Schema(description = "最小响应时间")
    @TableField("min_time")
    private Integer minTime;

    @Schema(description = "每秒接收KB")
    @TableField("receive_kb_per_second")
    private Double receiveKbPerSecond;

    @Schema(description = "每秒发送KB")
    @TableField("sent_kb_per_second")
    private Double sentKbPerSecond;

    @Schema(description = "请求路径和参数")
    @TableField("request_location")
    private String requestLocation;

    @Schema(description = "请求头")
    @TableField("request_header")
    private String requestHeader;

    @TableField("request_body")
    private String requestBody;

    @Schema(description = "每秒请求速率")
    @TableField("request_rate")
    private Double requestRate;

    @Schema(description = "响应码")
    @TableField("response_code")
    private String responseCode;

    @Schema(description = "响应体")
    @TableField("response_data")
    private String responseData;

    @Schema(description = "响应头")
    @TableField("response_header")
    private String responseHeader;

    @Schema(description = "采样次数编号")
    @TableField("sampler_count")
    private Long samplerCount;

    @Schema(description = "请求名称")
    @TableField("sampler_label")
    private String samplerLabel;

    @Schema(description = "请求时间戳")
    @TableField("sample_time")
    private Long sampleTime;

    @Schema(description = "创建时间")
    @TableField("gmt_create")
    private Date gmtCreate;

    @Schema(description = "更新时间")
    @TableField("gmt_modified")
    private Date gmtModified;
}
