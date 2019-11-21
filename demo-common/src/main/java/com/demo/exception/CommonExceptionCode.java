package com.demo.exception;

/**
 * 各服务公共异常编码
 */
public enum CommonExceptionCode implements IExceptionCode {

    PARAM_INVALID(1000,"参数校验失败");

    private int code;
    private String message;

    private CommonExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return Integer.parseInt("000" + code);
    }

    public String getMessage() {
        return message;
    }

    public void toThrow(){
        throw new XXException(this);
    }
}
