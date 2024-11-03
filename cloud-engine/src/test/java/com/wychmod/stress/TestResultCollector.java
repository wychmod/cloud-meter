package com.wychmod.stress;

import org.apache.jmeter.assertions.AssertionResult;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.samplers.SampleEvent;

/**
 * @description: 收集结果数据
 * @author: wychmod
 * @date: 2024-11-03
 */
public class TestResultCollector extends ResultCollector {
    public TestResultCollector() {
    }

    public TestResultCollector(Summariser summariser) {

    }

    /**
     * 当样本事件发生时调用此方法
     * 该方法重写了父类的方法，用于处理样本事件
     * 它打印出事件的相关信息，如样本数量、请求和响应头、线程组信息、响应码和主机名
     * 同时，它还遍历所有的断言结果，并打印出断言的名称和失败信息（如果有）
     *
     * @param event 样本事件对象，包含事件的相关信息和结果
     */
    @Override
    public void sampleOccurred(SampleEvent event) {
        // 调用父类的方法
        super.sampleOccurred(event);

        // 打印样本数量
        System.out.println(event.getResult().getSampleCount());
        // 打印请求头
        System.out.println(event.getResult().getRequestHeaders());
        // 打印响应头
        System.out.println(event.getResult().getResponseHeaders());
        // 打印线程组信息
        System.out.println(event.getResult().getGroupThreads());
        // 打印响应码
        System.out.println(event.getResult().getResponseCode());
        // 打印主机名
        System.out.println(event.getHostname());
        // 打印响应数据
        System.out.println(event.getResult().getResponseDataAsString());

        // 进行断言
        AssertionResult[] assertionResults = event.getResult().getAssertionResults();
        for (AssertionResult assertionResult : assertionResults) {
            // 打印断言结果的名称和失败信息
            System.out.println("name"+assertionResult.getName()+"failureMessage"+assertionResult.getFailureMessage());
        }
    }
}
