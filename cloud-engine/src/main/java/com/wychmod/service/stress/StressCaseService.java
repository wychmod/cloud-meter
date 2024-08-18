package com.wychmod.service.stress;

import com.wychmod.dto.stress.StressCaseDTO;
import com.wychmod.req.stress.StressCaseSaveReq;
import com.wychmod.req.stress.StressCaseUpdateReq;

public interface StressCaseService {

    StressCaseDTO findById(Long projectId, Long caseId);

    int delete(Long id, Long projectId);

    int save(StressCaseSaveReq stressCaseSaveReq);

    int update(StressCaseUpdateReq stressCaseUpdateReq);

    void  execute(Long projectId, Long caseId);

}
