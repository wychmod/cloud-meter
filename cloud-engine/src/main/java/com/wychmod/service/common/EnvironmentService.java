package com.wychmod.service.common;

import com.wychmod.dto.common.EnvironmentDTO;
import com.wychmod.req.common.EnvironmentSaveReq;
import com.wychmod.req.common.EnvironmentUpdateReq;

import java.util.List;

public interface EnvironmentService {
    List<EnvironmentDTO> list(Long projectId);

    int save(EnvironmentSaveReq environmentSaveReq);

    int update(EnvironmentUpdateReq environmentUpdateReq);

    int delete(Long id, Long projectId);

}

