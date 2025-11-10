package com.wychmod.service.stress.core;

import cn.hutool.core.util.IdUtil;
import com.wychmod.dto.ReportDTO;
import com.wychmod.model.StressCaseDO;
import com.wychmod.service.common.ResultSenderService;
import com.wychmod.util.StressTestUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

/**
 * @description: 压测引擎抽象类
 * @author: wychmod
 * @date: 2024-11-03
 */
@Data
@Slf4j
public abstract class BaseStressEngine {

    /**
     * 最终的测试计划
     */
    private HashTree testPlanHashTree;

    /**
     * 测试引擎
     */
    private StandardJMeterEngine engine;

    /**
     * 测试用例
     */
    private StressCaseDO stressCaseDO;

    /**
     * 测试报告
     */
    private ReportDTO reportDTO;


    /**
     * spring的应用上下文
     */
    private ApplicationContext applicationContext;


    public void startStressTest() {

        //初始化测试引擎
        this.initStressEngine();

        //组装测试计划 抽象方法
        this.assembleTestPlan();

        //方便调试使用，可以不用
        this.hashTree2Jmx();

        //运行测试
        this.run();

        //运行完用例后，清理相关的资源
        this.clearData();

        //更新测试报告
        this.updateReport();

    }

    public EngineSampleCollector getEngineSampleCollector(ResultSenderService resultSenderService) {
        // Summariser对象
        Summariser summer = null;
        // Summariser名称
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (!summariserName.isEmpty()) {
            // 创建Summariser对象
            summer = new Summariser(summariserName);
        }
        // 结果收集器
        EngineSampleCollector collector = new EngineSampleCollector(resultSenderService, this.reportDTO, this.stressCaseDO, summer);
        // 调整收集器名称
        collector.setName(stressCaseDO.getName());
        collector.setEnabled(Boolean.TRUE);

        return collector;

    }

    private void updateReport() {
    }

    private void clearData() {
        
    }

    private void run() {
        if (Objects.nonNull(this.testPlanHashTree)){
            this.engine.configure(this.testPlanHashTree);
            this.engine.run();
        }
    }

    private void hashTree2Jmx() {
        try {
            StressTestUtil.initJmeterProperties();
            SaveService.loadProperties();
            String localJmxPath = System.getProperty("user.dir") + File.separator+"static"+File.separator + IdUtil.simpleUUID()+".jmx";
            SaveService.saveTree(testPlanHashTree, new FileOutputStream(localJmxPath));
        }catch (Exception e){
            log.error("保存本地jmx失败");
        }
        
    }

    protected abstract void assembleTestPlan();


    public void initStressEngine() {
        this.engine = StressTestUtil.getJMeterEngine();
    }
}
