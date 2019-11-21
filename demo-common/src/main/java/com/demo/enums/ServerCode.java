package com.demo.enums;

/**
 * 系统码枚举类
 * 每个服务或系统对应一个系统码，响应码会以系统码+具体的错误码形式返回
 */
public enum ServerCode {

    PRODUCER_SERVICE("101", "生产服务"),
    CONSUMER_SERVICE("102", "消费服务");

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
