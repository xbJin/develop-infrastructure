package com.develop.infrastructure.design.Strategy.pay;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:23:04
 * @description : 支付上下文 - 负责管理支付策略
 */
public class PayContext {

    private PayStrategy payStrategy;


    public void setPayStrategy(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    /**
     * 支付
     *
     * @return
     */
    public boolean pay(double amount) {
        if (payStrategy == null) {
            throw new IllegalStateException("请选择支付方式");
        }
        return payStrategy.pay(amount);
    }


    public String getChannelName() {
        return payStrategy.getChannelName() != null ? payStrategy.getChannelName() : "未选择";
    }

    /**
     * 测试使用
     * @param args
     */
    public static void main(String[] args) {
        PayContext payContext = new PayContext();
        payContext.setPayStrategy(new AliPayStrategy());
        payContext.pay(99.99);
    }

}
