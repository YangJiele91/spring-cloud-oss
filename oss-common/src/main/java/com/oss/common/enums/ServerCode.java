package com.oss.common.enums;

/**
 * 系统码枚举类
 * 每个服务或系统对应一个系统码，响应码会以系统码+具体的错误码形式返回
 */
public enum ServerCode {

    BASE_SERVICE("101", "基础服务"),
    USER_SERVICE("102", "用户服务");

    ServerCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
