package com.wychmod.util;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.util.JMeterUtils;
import java.io.File;
import java.util.Objects;

public class StressTestUtil {

    /**
     * 获取JMeter安装目录的路径
     * 此方法通过类加载器查找JMeter资源文件的位置，以确定JMeter的安装目录
     * 使用类加载器确保了即使在不同环境下，也能正确地定位到JMeter的安装路径
     *
     * @return JMeter安装目录的路径
     * @throws RuntimeException 如果无法定位JMeter资源，则抛出运行时异常
     */
    public static String getJmeterHome() {
        try {
            // 使用StressTestUtil类的类加载器查找JMeter资源文件的位置，并返回其路径
            return Objects.requireNonNull(StressTestUtil.class.getClassLoader().getResource("jmeter")).getPath();
        } catch (Exception e) {
            // 如果查找资源失败，抛出自定义的运行时异常
            throw new RuntimeException("Failed to locate JMeter resources", e);
        }
    }

    /**
     * 获取JMeter安装目录下的bin子目录路径
     * @return JMeter安装目录下的bin子目录路径
     */
    public static String getJmeterHomeBin() {
        return getJmeterHome() + File.separator + "bin";
    }

    /**
     * 初始化JMeter属性 此处为1
     * 该方法通过加载JMeter的配置文件并设置一些基本属性来初始化JMeter环境
     * 1. 环境初始化
     * 2. 测试脚本
     * 3. 用例执行
     * 4. 结果收集
     */
    public static void initJmeterProperties() {
        String jmeterHomeBin = getJmeterHomeBin();

        // 确保路径正确拼接，这里直接用jmeterHomeBin已包含separator
        String jmeterPropertiesPath = jmeterHomeBin + File.separator +"jmeter.properties";

        // 加载JMeter的配置文件
        JMeterUtils.loadJMeterProperties(jmeterPropertiesPath);

        // 设置JMeter的安装目录
        JMeterUtils.setJMeterHome(getJmeterHome());
        // 设置乱码问题
        JMeterUtils.setProperty("sampleresult.default.encoding","utf-8");
        // 初始化本地环境
        JMeterUtils.initLocale();
    }

    /**
     * 获取JMeter引擎实例
     * 在创建引擎实例之前，首先初始化JMeter的属性
     * @return StandardJMeterEngine实例
     */
    public static StandardJMeterEngine getJMeterEngine(){
        initJmeterProperties();
        return new StandardJMeterEngine();
    }

}
