package com.wychmod.service.stress.core;

import cn.hutool.core.util.IdUtil;
import com.wychmod.dto.ReportDTO;
import com.wychmod.model.StressCaseDO;
import com.wychmod.service.common.ResultSenderService;
import com.wychmod.service.stress.core.EngineSampleCollector;
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


    /**
     * 启动压测流程
     * 执行完整的压测流程，包括初始化引擎、组装测试计划、运行测试、清理资源和更新报告
     */
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

    /**
     * 获取引擎样本收集器
     * 
     * @param resultSenderService 结果发送服务
     * @return EngineSampleCollector 引擎样本收集器实例
     */
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

    /**
     * 更新测试报告
     * 测试执行完成后更新相关测试报告信息
     */
    private void updateReport() {
    }

    /**
     * 清理数据
     * 测试执行完成后清理相关资源和数据
     */
    private void clearData() {
        
    }

    /**
     * 运行测试
     * 配置并运行压测引擎
     */
    private void run() {
        if (Objects.nonNull(this.testPlanHashTree)){
            this.engine.configure(this.testPlanHashTree);
            this.engine.run();
        }
    }

    /**
     * 将HashTree保存为JMX文件
     * 用于调试目的，将测试计划保存为本地JMX文件
     */
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

    /**
     * 组装测试计划
     * 抽象方法，由子类实现具体的测试计划组装逻辑
     */
    protected abstract void assembleTestPlan();


    /**
     * 初始化压测引擎
     * 创建并初始化StandardJMeterEngine实例
     */
    public void initStressEngine() {
        this.engine = StressTestUtil.getJMeterEngine();
    }
}