package com.wychmod.exception;

import com.wychmod.enums.BizCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 自定义业务异常类
 * 用于封装业务逻辑中的异常情况，包含状态码、错误信息和详细信息
 * 继承自RuntimeException，支持在运行时抛出
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException{
    /**
     * 异常状态码
     */
    private int code;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 异常详细信息
     */
    private String detail;

    /**
     * 构造函数，使用指定的状态码和错误信息创建异常
     * @param code 异常状态码
     * @param message 错误信息
     */
    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    /**
     * 构造函数，使用业务码枚举创建异常
     * @param bizCodeEnum 业务码枚举，包含状态码和对应的错误信息
     */
    public BizException(BizCodeEnum bizCodeEnum){
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }

    /**
     * 构造函数，使用业务码枚举和异常对象创建异常
     * @param bizCodeEnum 业务码枚举，包含状态码和对应的错误信息
     * @param e 引起此异常的原始异常对象
     */
    public BizException(BizCodeEnum bizCodeEnum,Exception e){
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
        this.detail = e.toString();
    }
}
