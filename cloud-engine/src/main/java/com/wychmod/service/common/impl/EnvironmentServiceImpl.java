package com.wychmod.service.common.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wychmod.dto.common.EnvironmentDTO;
import com.wychmod.mapper.EnvironmentMapper;
import com.wychmod.mapper.ProjectMapper;
import com.wychmod.model.EnvironmentDO;
import com.wychmod.model.ProjectDO;
import com.wychmod.req.common.EnvironmentSaveReq;
import com.wychmod.req.common.EnvironmentUpdateReq;
import com.wychmod.service.common.EnvironmentService;
import com.wychmod.util.SpringBeanUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {
    @Resource
    private EnvironmentMapper environmentMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Override
    public List<EnvironmentDTO> list(Long projectId) {
        LambdaQueryWrapper<EnvironmentDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EnvironmentDO::getProjectId,projectId);
        List<EnvironmentDO> environmentDOList = environmentMapper.selectList(wrapper);
        return SpringBeanUtil.copyListProperties(environmentDOList,EnvironmentDTO.class);
    }

    @Override
    public int save(EnvironmentSaveReq environmentSaveReq) {

        //判断一下这个项目存在不存在
        ProjectDO projectDO = projectMapper.selectById(environmentSaveReq.getProjectId());
        if (projectDO!=null){
            EnvironmentDO environmentDO = SpringBeanUtil.copyProperties(environmentSaveReq, EnvironmentDO.class);
            return environmentMapper.insert(environmentDO);
        }
        return 0;
    }

    @Override
    public int update(EnvironmentUpdateReq environmentUpdateReq) {
        // 校验项目是否存在
        ProjectDO projectDO = projectMapper.selectById(environmentUpdateReq.getProjectId());
        // 存在则保存
        if (projectDO != null) {
            EnvironmentDO environmentDO = BeanUtil.copyProperties(environmentUpdateReq, EnvironmentDO.class);
            return environmentMapper.updateById(environmentDO);
        }
        return 0;
    }



    @Override
    public  int delete(Long id, Long projectId){
        LambdaQueryWrapper<EnvironmentDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EnvironmentDO::getId,id);
        wrapper.eq(EnvironmentDO::getProjectId,projectId);
        return environmentMapper.delete(wrapper);
    }
}
