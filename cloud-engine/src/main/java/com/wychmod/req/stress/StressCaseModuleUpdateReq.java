package com.wychmod.req.stress;

import lombok.Data;

@Data
public class StressCaseModuleUpdateReq  {
    private Long id;

    private Long projectId;

    private String name;
}
