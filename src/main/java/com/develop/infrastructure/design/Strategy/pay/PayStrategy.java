package com.develop.infrastructure.design.Strategy.pay;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:18:46
 * @description : 支付策略接口
 */
public interface PayStrategy {

    /**
     * 支付
     * @param amount  支付金额
     * @return  支付结果
     */
    boolean pay(double amount);

    /**
     * 支付渠道名称
     * @return
     */
    String getChannelName();
}
