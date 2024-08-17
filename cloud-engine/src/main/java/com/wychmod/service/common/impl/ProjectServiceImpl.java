package com.wychmod.service.common.impl;

import com.wychmod.dto.common.ProjectDTO;
import com.wychmod.mapper.ProjectMapper;
import com.wychmod.model.ProjectDO;
import com.wychmod.req.common.ProjectSaveReq;
import com.wychmod.req.common.ProjectUpdateReq;
import com.wychmod.service.common.ProjectService;
import com.wychmod.util.SpringBeanUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    /**
     * 获取项目列表
     *
     * @return 项目DTO列表
     */
    @Override
    public List<ProjectDTO> list() {

        // 从数据库中查询所有项目记录
        List<ProjectDO> projectDOS = projectMapper.selectList(null);

        // 将项目实体列表转换为项目DTO列表并返回
        return SpringBeanUtil.copyListProperties(projectDOS, ProjectDTO.class);
    }

    /**
     * 保存新项目
     *
     * @param projectSaveReq 包含新项目信息的请求对象
     * @return 保存操作影响的行数
     */
    @Override
    public int save(ProjectSaveReq projectSaveReq) {
        // 将请求对象转换为项目实体
        ProjectDO projectDO = SpringBeanUtil.copyProperties(projectSaveReq, ProjectDO.class);
        // 插入新项目到数据库并返回影响的行数
        return projectMapper.insert(projectDO);
    }

    /**
     * 更新现有项目
     *
     * @param projectUpdateReq 包含更新后项目信息的请求对象
     * @return 更新操作影响的行数
     */
    @Override
    public int update(ProjectUpdateReq projectUpdateReq) {
        // 将请求对象转换为项目实体
        ProjectDO projectDO = SpringBeanUtil.copyProperties(projectUpdateReq, ProjectDO.class);
        // 更新数据库中的项目信息并返回影响的行数
        return projectMapper.updateById(projectDO);
    }

    /**
     * 删除指定项目
     *
     * @param id 要删除的项目的ID
     * @return 删除操作影响的行数
     */
    @Override
    public int delete(Long id) {
        // 根据ID删除项目并返回影响的行数
        return projectMapper.deleteById(id);
    }
}
