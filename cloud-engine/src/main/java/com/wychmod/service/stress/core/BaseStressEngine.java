package com.wychmod.service.stress.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 压测引擎抽象类
 * @author: wychmod
 * @date: 2024-11-03
 */
@Data
@Slf4j
public abstract class BaseStressEngine {


    public void startStressTest() {

        //初始化测试引擎
        this.initStressEngine();


        // ToDo

    }


    public void initStressEngine() {
    }
}
