package com.wychmod.service.common;

import com.wychmod.dto.common.ProjectDTO;
import com.wychmod.req.common.ProjectDelReq;
import com.wychmod.req.common.ProjectSaveReq;
import com.wychmod.req.common.ProjectUpdateReq;

import java.util.List;

public interface ProjectService {

    /**
     * 获取用户项目列表
     */
    List<ProjectDTO> list();

    /**
     * 保存项目
     */
    int save(ProjectSaveReq projectSaveReq);

    /**
     * 更新项目
     */
    int update(ProjectUpdateReq projectUpdateReq);

    /**
     * 删除项目
     */
    int delete(Long id);
}
