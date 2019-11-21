package com.demo.enums;

import com.demo.exception.IExceptionCode;
import com.demo.exception.XXException;

/**
 * 统一封装返回前端code枚举类
 */
public enum ExceptionCode implements IExceptionCode {

    UNKNOWN_ERROR(9999, "未知异常"),

    PARAM_INVALID(1000,"参数校验失败"),
    PARA_NULL(1001, "参数为空");

    private int code;
    private String message;

    private ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return Integer.parseInt(ServerCode.CONSUMER_SERVICE.getCode() + code);
    }

    public String getMessage() {
        return message;
    }

    public void toThrow(){
        throw new XXException(this);
    }
}
