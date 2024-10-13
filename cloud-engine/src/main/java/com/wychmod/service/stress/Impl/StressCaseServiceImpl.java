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

    /**
     * 执行压力测试的详细步骤
     * 1. 查询用例详情：根据用例ID查询详细的测试用例信息
     * 2. 初始化测试报告：为本次测试创建一个新的测试报告
     * 3. 判断 压测类型：确定即将进行的压力测试的类型，以便于后续的测试计划和执行
     * 4. 初始化测试类型：根据判断出的测试类型进行初始化，确保测试环境和配置正确
     * 5. 组装测试计划：根据测试用例和测试类型组装具体的测试计划，包括测试的流程和步骤
     * 6. 执行测压：按照测试计划执行压力测试
     * 7. 发送测试结果明细：测试完成后，发送详细的测试结果，包括各项性能指标和测试数据
     * 8. 压测完成后清理数据：清理测试过程中产生的临时数据和资源，确保环境的整洁
     * 9. 通知压测结果：将测试结果通知给相关人员或系统，以便于他们了解测试情况
     **/
    @Override
    public void execute(Long projectId, Long caseId) {
        LambdaQueryWrapper<StressCaseDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StressCaseDO::getProjectId,projectId);
        wrapper.eq(StressCaseDO::getId,caseId);
        StressCaseDO stressCaseDO = stressCaseMapper.selectOne(wrapper);
        if (stressCaseDO != null) {
            // 初始化测试报告 todo
        }
    }
}
