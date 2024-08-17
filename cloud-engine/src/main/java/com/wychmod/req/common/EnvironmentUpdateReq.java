package com.wychmod.req.common;

import lombok.Data;

@Data
public class EnvironmentUpdateReq {
    private Long  id;

    private Long projectId;

    private String name;

    private String protocol;

    private String domain;

    private Integer port;

    private String description;

}
