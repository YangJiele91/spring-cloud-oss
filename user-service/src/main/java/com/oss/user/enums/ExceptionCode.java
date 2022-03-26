package com.oss.user.enums;

import com.oss.common.enums.ServerCode;
import com.oss.common.exception.IExceptionCode;
import com.oss.common.exception.XXException;

/**
 * 统一封装返回前端code枚举类
 */
public enum ExceptionCode implements IExceptionCode {

    UNKNOWN_ERROR(9999, "未知异常");

    private final int code;
    private final String message;

    ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return Integer.parseInt(ServerCode.USER_SERVICE.getCode() + code);
    }

    public String getMessage() {
        return message;
    }

    public void toThrow(){
        throw new XXException(this);
    }
}
