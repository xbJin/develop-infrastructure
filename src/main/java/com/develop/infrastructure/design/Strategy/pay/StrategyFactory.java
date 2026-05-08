package com.develop.infrastructure.design.Strategy.pay;

import com.develop.infrastructure.design.Strategy.log.ConsuoleLogStrategy;
import com.develop.infrastructure.design.Strategy.log.LogStrategy;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:39:50
 * @description : 策略工厂 - 根据条件创建合适的策略
 */
public class StrategyFactory {

    public static PayStrategy getPayStrategy(String channel){
        switch (channel.toLowerCase()){
            case "Alipay":
                return new AliPayStrategy();
            case "WeiXin":
                return new WeiXinPayStrategy();
            default:
                throw new IllegalStateException("不支持的支付方式");
        }
    }


    public static LogStrategy getLogStrategy(String env){
        switch (env.toLowerCase()){
            case "dev":
                return new ConsuoleLogStrategy();
            default:
                return new ConsuoleLogStrategy();
        }
    }

}
