package com.develop.infrastructure.design.Strategy.pay;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:20:37
 * @description :
 */
public class AliPayStrategy implements PayStrategy{
    @Override
    public boolean pay(double amount) {
        System.out.println("使用支付宝支付");
        return true;
    }

    @Override
    public String getChannelName() {
        return "Alipay";
    }
}
