package com.wychmod.service.stress.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wychmod.dto.stress.StressCaseDTO;
import com.wychmod.dto.stress.StressCaseModuleDTO;
import com.wychmod.mapper.StressCaseMapper;
import com.wychmod.mapper.StressCaseModuleMapper;
import com.wychmod.model.StressCaseDO;
import com.wychmod.model.StressCaseModuleDO;
import com.wychmod.req.stress.StressCaseModuleSaveReq;
import com.wychmod.req.stress.StressCaseModuleUpdateReq;
import com.wychmod.service.stress.StressCaseModelService;
import com.wychmod.util.SpringBeanUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StressCaseModelServiceImpl implements StressCaseModelService {

    @Resource
    private StressCaseModuleMapper stressCaseModuleMapper;
    @Resource
    private StressCaseMapper stressCaseMapper;
    @Override
    public List<StressCaseModuleDTO> list(Long projectId) {
        LambdaQueryWrapper<StressCaseModuleDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StressCaseModuleDO::getProjectId,projectId);
        List<StressCaseModuleDO> stressCaseModuleDOList = stressCaseModuleMapper.selectList(wrapper);
        List<StressCaseModuleDTO> stressCaseModuleDTOList = SpringBeanUtil.copyListProperties(stressCaseModuleDOList, StressCaseModuleDTO.class);
        stressCaseModuleDTOList.forEach(source->{
            //查询压测模块下的关联用例
            LambdaQueryWrapper<StressCaseDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(StressCaseDO::getModuleId,source.getId()).orderByDesc(StressCaseDO::getId);

            List<StressCaseDO> stressCaseDOList = stressCaseMapper.selectList(queryWrapper);
            List<StressCaseDTO> stressCaseDTOS = SpringBeanUtil.copyListProperties(stressCaseDOList, StressCaseDTO.class);
            source.setList(stressCaseDTOS);
        });
        return stressCaseModuleDTOList;
    }

    @Override
    public StressCaseModuleDTO findById(Long projectId, Long moduleId) {
        LambdaQueryWrapper<StressCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseModuleDO::getProjectId,projectId).eq(StressCaseModuleDO::getId,moduleId);
        StressCaseModuleDO stressCaseModuleDO = stressCaseModuleMapper.selectOne(queryWrapper);
        if (stressCaseModuleDO==null){
            return null;
        }
        StressCaseModuleDTO stressCaseModuleDTO = SpringBeanUtil.copyProperties(stressCaseModuleDO, StressCaseModuleDTO.class);

        //查看压测模型下关联的用例;
        LambdaQueryWrapper<StressCaseDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StressCaseDO::getProjectId,projectId).orderByDesc(StressCaseDO::getId);
        List<StressCaseDO> stressCaseDOList = stressCaseMapper.selectList(wrapper);
        List<StressCaseDTO> stressCaseDTOList = SpringBeanUtil.copyListProperties(stressCaseDOList, StressCaseDTO.class);
        stressCaseModuleDTO.setList(stressCaseDTOList);

        return stressCaseModuleDTO;
    }

    @Override
    public int delete(Long id, Long projectId) {
        LambdaQueryWrapper<StressCaseModuleDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StressCaseModuleDO::getId,id).eq(StressCaseModuleDO::getProjectId,projectId);
        int delete = stressCaseModuleMapper.delete(wrapper);
        if (delete>0){
            LambdaQueryWrapper<StressCaseDO> querywrapper = new LambdaQueryWrapper<>();
            querywrapper.eq(StressCaseDO::getModuleId,id);
            stressCaseMapper.delete(querywrapper);
        }
        return delete;
    }

    @Override
    public int save(StressCaseModuleSaveReq stressCaseModuleSaveReq) {
        StressCaseModuleDO stressCaseModuleDO = SpringBeanUtil.copyProperties(stressCaseModuleSaveReq, StressCaseModuleDO.class);
        return stressCaseModuleMapper.insert(stressCaseModuleDO);
    }

    @Override
    public int update(StressCaseModuleUpdateReq stressCaseModuleUpdateReq) {
        StressCaseModuleDO stressCaseModuleDO = SpringBeanUtil.copyProperties(stressCaseModuleUpdateReq, StressCaseModuleDO.class);
        LambdaQueryWrapper<StressCaseModuleDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StressCaseModuleDO::getProjectId,stressCaseModuleDO.getProjectId()).eq(StressCaseModuleDO::getId,stressCaseModuleDO.getId());
        return stressCaseModuleMapper.update(stressCaseModuleDO, queryWrapper);
    }
}
