package com.wychmod.dto.common;
import lombok.Data;

import java.util.Date;

/**
 * 项目数据传输对象
 * <p>
 * 用于封装项目的基本信息，包括项目ID、项目管理员、项目名称、描述以及创建和修改时间等信息
 * </p>
 */
@Data
public class ProjectDTO {

    /**
     * 项目唯一标识符
     */
    private Long id;

    /**
     * 项目管理员ID
     */
    private Long projectAdmin;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目描述
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
}
