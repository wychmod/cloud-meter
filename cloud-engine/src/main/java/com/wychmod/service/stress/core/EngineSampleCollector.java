package com.wychmod.service.stress.core;

import com.alibaba.fastjson2.JSON;
import com.wychmod.dto.ReportDTO;
import com.wychmod.dto.StressSampleResultDTO;
import com.wychmod.dto.common.CaseInfoDTO;
import com.wychmod.enums.TestTypeEnum;
import com.wychmod.model.StressCaseDO;
import com.wychmod.service.common.ResultSenderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.assertions.AssertionResult;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.SamplingStatCalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 引擎采样器
 * @author: wychmod
 * @date: 2024-11-03
 */
@Slf4j
public class EngineSampleCollector extends ResultCollector {

    private final Map<String, SamplingStatCalculator> calculatorMap = new HashMap<>();
    private ResultSenderService resultSenderService;
    private ReportDTO reportDTO;
    private StressCaseDO stressCaseDO;

    public EngineSampleCollector()
    {
        super();
    }

    public EngineSampleCollector(ResultSenderService resultSenderService, ReportDTO reportDTO, StressCaseDO stressCaseDO, Summariser summariser)
    {
        super(summariser);
        this.reportDTO = reportDTO;
        this.resultSenderService = resultSenderService;
        this.stressCaseDO = stressCaseDO;
    }

    /**
     * 当样本事件发生时调用此方法
     * 该方法处理样本事件，计算统计信息，并发送结果
     * 1. 获取event事件结果
     * 2. 根据结果的标签，获取对应的采样统计计算器
     * 3. 创建结果对象，封装采样统计计算器的结果
     * 4. 将结果转换成json，发送mq
     * @param event 样本事件对象，包含样本数据和事件信息
     */
    @Override
    public void sampleOccurred(SampleEvent event)
    {
        super.sampleOccurred(event);

        // 获取event事件结果
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

        //封装采样器结果数据
        StressSampleResultDTO stressSampleResultDTO = new StressSampleResultDTO();
        stressSampleResultDTO.setReportId(reportDTO.getId());
        stressSampleResultDTO.setSampleTime(result.getTimeStamp());
        stressSampleResultDTO.setSampleLabel(result.getSampleLabel());
        stressSampleResultDTO.setSampleCount(samplingStatCalculator.getMax().intValue());
        stressSampleResultDTO.setMaxTime(samplingStatCalculator.getMax().intValue());
        stressSampleResultDTO.setMeanTime(samplingStatCalculator.getMean());
        stressSampleResultDTO.setMinTime(samplingStatCalculator.getMin().intValue());
        stressSampleResultDTO.setErrorpercentage(samplingStatCalculator.getErrorPercentage());
        stressSampleResultDTO.setErrorCount(samplingStatCalculator.getErrorCount());
        stressSampleResultDTO.setRequestRate(samplingStatCalculator.getRate());
        stressSampleResultDTO.setReceiveKBPerSecound(samplingStatCalculator.getKBPerSecond());
        stressSampleResultDTO.setSendKBPerSecound(samplingStatCalculator.getSentKBPerSecond());
        stressSampleResultDTO.setRequestLocation(event.getResult().getUrlAsString());
        stressSampleResultDTO.setRequestHeader(event.getResult().getRequestHeaders());
        stressSampleResultDTO.setRequestBody(event.getResult().getSamplerData());
        stressSampleResultDTO.setResponseCode(event.getResult().getResponseCode());
        stressSampleResultDTO.setResponseHeader(event.getResult().getResponseHeaders());
        stressSampleResultDTO.setResponseDate(event.getResult().getResponseData());
        stressSampleResultDTO.setSampleCount(samplingStatCalculator.getCount());

        // 断言
        AssertionResult[] assertionResults = event.getResult().getAssertionResults();
        StringBuilder assertMsg = new StringBuilder();
        for (AssertionResult assertionResult : assertionResults) {
            assertMsg.append("name").append(assertionResult.getName()).append("msg=").append(assertionResult.getFailureMessage()).append(",");
        }
        stressSampleResultDTO.setAssertInfo(assertMsg.toString());

        // 将结果对象转换为JSON字符串并记录日志
        String resultJson = JSON.toJSONString(stressSampleResultDTO);
        log.info(resultJson);

        // 创建用例信息对象并发送结果
        CaseInfoDTO caseInfoDTO = new CaseInfoDTO(stressCaseDO.getId(),stressCaseDO.getModuleId(),stressCaseDO.getName());
        resultSenderService.sendResult(caseInfoDTO, TestTypeEnum.STRESS, resultJson);
    }
}
