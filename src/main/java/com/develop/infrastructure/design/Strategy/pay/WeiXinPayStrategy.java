package com.develop.infrastructure.design.Strategy.pay;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:21:31
 * @description :
 */
public class WeiXinPayStrategy implements PayStrategy{
    @Override
    public boolean pay(double amount) {
        System.out.println("使用微信支付");
        return true;
    }

    @Override
    public String getChannelName() {
        return "WinXin";
    }
}
