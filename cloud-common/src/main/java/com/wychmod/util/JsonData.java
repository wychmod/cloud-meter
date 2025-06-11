package com.wychmod.util;

import com.alibaba.fastjson.JSON;
import com.wychmod.enums.BizCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 成功，不传入数据
     * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    /**
     *  成功，传入数据
     * @param data
     * @return
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }

    /**
     * 失败，传入描述信息
     * @param msg
     * @return
     */
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

    /**
     * 自定义状态码和错误信息
     * @param code
     * @param msg
     * @return
     */
    public static JsonData buildCodeAndMsg(int code, String msg) {
        return new JsonData(code, null, msg);
    }

    /**
     * 自定义状态码和错误信息
     * @param codeEnum
     * @return
     */
    public static JsonData buildResult(BizCodeEnum codeEnum){
        return JsonData.buildCodeAndMsg(codeEnum.getCode(),codeEnum.getMessage());
    }

    public  boolean isSuccess(){
        return code == 0;
    }
}
