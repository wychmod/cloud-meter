package com.wychmod.service.stress;

import com.wychmod.dto.stress.StressCaseModuleDTO;
import com.wychmod.req.stress.StressCaseModuleSaveReq;
import com.wychmod.req.stress.StressCaseModuleUpdateReq;

import java.util.List;

public interface StressCaseModelService {

    List<StressCaseModuleDTO> list(Long projectId);

    StressCaseModuleDTO findById(Long projectId, Long moduleId);

    int delete(Long id, Long projectId);

    int save(StressCaseModuleSaveReq stressCaseModuleSaveReq);

    int update(StressCaseModuleUpdateReq stressCaseModuleUpdateReq);
}
