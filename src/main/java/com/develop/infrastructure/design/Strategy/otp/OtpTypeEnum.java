package com.develop.infrastructure.design.Strategy.otp;

public enum OtpTypeEnum {

    SMS("SMS","短信"),
    EMAIL("EMAIL","邮箱"),
    ;


    private final String code;

    private final String desc;

    OtpTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
