package com.wychmod.service.stress.core;

import com.wychmod.dto.ReportDTO;
import com.wychmod.model.StressCaseDO;
import com.wychmod.service.common.ResultSendService;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.SamplingStatCalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: wychmod
 * @date: 2024-11-03
 */
public class EngineSampleCollector extends ResultCollector {

    private Map<String, SamplingStatCalculator> calculatorMap = new HashMap<>();
    private ResultSendService resultSendService;
    private ReportDTO reportDTO;
    private StressCaseDO stressCaseDO;

    public EngineSampleCollector()
    {
        super();
    }

    public EngineSampleCollector(ResultSendService resultSendService, ReportDTO reportDTO, StressCaseDO stressCaseDO, Summariser summariser)
    {
        super(summariser);
        this.reportDTO = reportDTO;
        this.resultSendService = resultSendService;
        this.stressCaseDO = stressCaseDO;
    }

    @Override
    public void sampleOccurred(SampleEvent event)
    {
        super.sampleOccurred(event);

        // 获取事件的结果
        SampleResult result = event.getResult();

        // 获取结果的样本标签
        String sampleLabel = result.getSampleLabel();

        // 从计算器映射中获取对应的采样统计计算器
        SamplingStatCalculator samplingStatCalculator = calculatorMap.get(sampleLabel);

        // 如果没有找到对应的采样统计计算器，则创建一个新的计算器并添加样本结果
        if (samplingStatCalculator == null) {
            samplingStatCalculator = new SamplingStatCalculator(sampleLabel);
            samplingStatCalculator.addSample(result);
            calculatorMap.put(sampleLabel, samplingStatCalculator);
        }else {
            // 如果找到了对应的采样统计计算器，则直接添加样本结果
            samplingStatCalculator.addSample(result);
        }
    }
}
