package com.demo.exception;

/**
 * 自定义异常
 */
public class XXException extends RuntimeException {

    private int code = 9999;
    private String msg;

    public XXException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public XXException(IExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.code = exceptionCode.getCode();
        this.msg = exceptionCode.getMessage();
    }

    public XXException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public XXException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    public XXException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
