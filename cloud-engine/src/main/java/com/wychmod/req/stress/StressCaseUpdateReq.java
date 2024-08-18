package com.wychmod.req.stress;

import lombok.Data;

@Data
public class StressCaseUpdateReq {

    private Long id;

    private Long moduleId;


    private Long enviromentId;


    private String name;


    private String description;

    private String assertion;


    private String relation;


    private String threadGroupConfig;


    private String jmxUrl;

    private String path;


    private String method;

    private String query;

    private String header;

    private String body;

    private String bodyType;


}