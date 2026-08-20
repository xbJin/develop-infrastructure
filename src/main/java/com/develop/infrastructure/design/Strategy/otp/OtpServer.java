package com.develop.infrastructure.design.Strategy.otp;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:51:56
 * @description : otp 服务接口
 */
public interface OtpServer {

    // todo jinxiaobo 待完善
    boolean sendOtp(String msg);

    // 验证
    boolean verifyOtp(String msg);

}
