package com.wychmod.service.stress;

import com.wychmod.dto.stress.StressCaseDTO;
import com.wychmod.req.stress.StressCaseSaveReq;
import com.wychmod.req.stress.StressCaseUpdateReq;

/**
 * 压测用例服务接口
 * <p>
 * 定义了压测用例的相关操作，包括查询、保存、更新、删除和执行压测用例等功能
 * </p>
 */
public interface StressCaseService {

    /**
     * 根据项目ID和用例ID查找压测用例
     *
     * @param projectId 项目ID
     * @param caseId    用例ID
     * @return 压测用例数据传输对象
     */
    StressCaseDTO findById(Long projectId, Long caseId);

    /**
     * 删除指定的压测用例
     *
     * @param id        用例ID
     * @param projectId 项目ID
     * @return 删除操作影响的行数
     */
    int delete(Long id, Long projectId);

    /**
     * 保存压测用例
     *
     * @param stressCaseSaveReq 压测用例保存请求对象
     * @return 保存操作影响的行数
     */
    int save(StressCaseSaveReq stressCaseSaveReq);

    /**
     * 更新压测用例
     *
     * @param stressCaseUpdateReq 压测用例更新请求对象
     * @return 更新操作影响的行数
     */
    int update(StressCaseUpdateReq stressCaseUpdateReq);

    /**
     * 执行指定的压测用例
     *
     * @param projectId 项目ID
     * @param caseId    用例ID
     */
    void execute(Long projectId, Long caseId);

}
