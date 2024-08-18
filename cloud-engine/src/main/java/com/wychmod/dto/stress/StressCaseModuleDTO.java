package com.wychmod.dto.stress;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StressCaseModuleDTO  {


    private Long id;

    private Long projectId;

    private String name;

    private String description;

    private Date gmtCreate;

    private Date gmtModified;

    private List<StressCaseDTO> list;
}
