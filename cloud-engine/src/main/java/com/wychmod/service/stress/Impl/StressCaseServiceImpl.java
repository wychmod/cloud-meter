package com.wychmod.service.stress.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wychmod.dto.stress.StressCaseDTO;
import com.wychmod.mapper.StressCaseMapper;
import com.wychmod.model.StressCaseDO;
import com.wychmod.req.stress.StressCaseSaveReq;
import com.wychmod.req.stress.StressCaseUpdateReq;
import com.wychmod.service.stress.StressCaseService;
import com.wychmod.util.SpringBeanUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class StressCaseServiceImpl implements StressCaseService {

    @Resource
    private StressCaseMapper stressCaseMapper;

    @Override
    public StressCaseDTO findById(Long projectId, Long caseId) {
        LambdaQueryWrapper<StressCaseDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StressCaseDO::getProjectId,projectId);
        wrapper.eq(StressCaseDO::getId,caseId);
        StressCaseDO stressCaseDO = stressCaseMapper.selectOne(wrapper);
        return SpringBeanUtil.copyProperties(stressCaseDO,StressCaseDTO.class);
    }

    @Override
    public int delete(Long id, Long projectId) {
        LambdaQueryWrapper<StressCaseDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StressCaseDO::getId,id);
        wrapper.eq(StressCaseDO::getProjectId,projectId);
        return stressCaseMapper.delete(wrapper);
    }

    @Override
    public int save(StressCaseSaveReq stressCaseSaveReq) {
        StressCaseDO stressCaseDO = SpringBeanUtil.copyProperties(stressCaseSaveReq, StressCaseDO.class);
        return  stressCaseMapper.insert(stressCaseDO);
    }

    @Override
    public int update(StressCaseUpdateReq stressCaseUpdateReq) {
        StressCaseDO stressCaseDO = SpringBeanUtil.copyProperties(stressCaseUpdateReq, StressCaseDO.class);
        LambdaQueryWrapper<StressCaseDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StressCaseDO::getId,stressCaseDO.getId());
        return stressCaseMapper.update(stressCaseDO,wrapper);
    }

    @Override
    public void execute(Long projectId, Long caseId) {

    }
}
