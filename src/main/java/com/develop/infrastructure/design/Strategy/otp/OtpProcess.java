package com.develop.infrastructure.design.Strategy.otp;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:55:46
 * @description : Otp处理接口,定义统一的Otp处理流程
 */
public interface OtpProcess {


    OtpTypeEnum getOtpType();

    void sendOtp(String msg);

    void verifyOtp(String msg);
}
