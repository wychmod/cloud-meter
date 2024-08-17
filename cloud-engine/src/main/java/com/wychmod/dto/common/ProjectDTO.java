package com.wychmod.dto.common;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectDTO {

    private Long id;

    private Long projectAdmin;

    private String name;

    private String description;

    private Date gmtCreate;

    private Date gmtModified;
}
