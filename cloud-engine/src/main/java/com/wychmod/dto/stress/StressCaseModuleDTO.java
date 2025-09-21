package com.wychmod.dto.stress;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 压测用例模块数据传输对象
 * 用于封装压测用例模块的基本信息，包括模块ID、所属项目ID、模块名称、描述以及关联的压测用例列表等信息
 */
@Data
public class StressCaseModuleDTO  {


    /**
     * 模块唯一标识符
     */
    private Long id;

    /**
     * 所属项目ID
     */
    private Long projectId;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    private Date gmtModified;

    /**
     * 关联的压测用例列表
     */
    private List<StressCaseDTO> list;
}
