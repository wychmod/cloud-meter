package com.wychmod.req.common;

import lombok.Data;

@Data
public class ProjectUpdateReq {

    private Long id;

    private String name;

    private String description;
}
