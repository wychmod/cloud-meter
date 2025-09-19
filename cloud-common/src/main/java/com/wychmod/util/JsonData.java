package com.wychmod.util;

import com.alibaba.fastjson.JSON;
import com.wychmod.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的JSON数据返回格式封装类
 * 用于在Web应用中封装接口返回数据，包含状态码、数据和描述信息
 * 提供了多种构建成功和失败响应的静态方法
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonData {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 数据
     */
    private Object data;

    /**
     * 描述
     */
    private String msg;

    /**
     * 根据提供的类型引用获取对应类型的数据
     * 本方法使用JSON库将内部存储的数据序列化为JSON字符串，然后将该字符串解析为由typeReference参数指定的类型
     * 这种设计模式常用于处理需要在不同类型间转换数据的场景
     *
     * @param typeReference 类型引用，用于指定返回值的类型
     * @param <T>           返回值的泛型类型
     * @return              转换后的数据，类型由typeReference参数指定
     */
    public <T> T getData(Class<T> typeReference){
        return JSON.parseObject(JSON.toJSONString(data),typeReference);
    }

    /**
     * 构建成功响应，不包含数据
     * @return 包含成功状态码(0)的JsonData实例
     */
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    /**
     * 构建成功响应，包含指定数据
     * @param data 成功时需要返回的数据
     * @return 包含成功状态码(0)和数据的JsonData实例
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }

    /**
     * 构建错误响应，包含错误描述信息
     * @param msg 错误描述信息
     * @return 包含失败状态码(-1)和错误信息的JsonData实例
     */
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

    /**
     * 构建自定义状态码和错误信息的响应
     * @param code 自定义状态码
     * @param msg  错误描述信息
     * @return 包含指定状态码和错误信息的JsonData实例
     */
    public static JsonData buildCodeAndMsg(int code, String msg) {
        return new JsonData(code, null, msg);
    }

    /**
     * 根据业务枚举构建响应
     * @param codeEnum 业务状态码枚举
     * @return 包含枚举中指定状态码和消息的JsonData实例
     */
    public static JsonData buildResult(BizCodeEnum codeEnum){
        return JsonData.buildCodeAndMsg(codeEnum.getCode(),codeEnum.getMessage());
    }

    /**
     * 判断当前响应是否为成功状态
     * @return 当状态码为0时返回true，否则返回false
     */
    public  boolean isSuccess(){
        return code == 0;
    }
}
