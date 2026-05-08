package com.develop.infrastructure.design.Strategy.otp;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:51:56
 * @description : otp 服务接口
 */
public interface OtpServer {

    // 发送
    boolean sendOtp(String msg);

    // 验证
    boolean verifyOtp(String msg);

}
