package com.develop.infrastructure.design.Strategy.log;

import com.develop.infrastructure.design.Strategy.pay.StrategyFactory;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:33:45
 * @description :
 */
public class LogManager {

    private LogStrategy logStrategy;


    public void setLogStrategy(LogStrategy logStrategy){
        this.logStrategy = logStrategy;
    }

    public void log(String message,String level){
        if (logStrategy == null){
            throw new IllegalStateException("请配置日志策略");
        }
        logStrategy.log(message,level);
    }

    public void info(String message){
        log(message,"INFO");
    }

    public void error(String message){
        log(message,"ERROR");
    }


    public static void main(String[] args) {
        LogManager logManager = new LogManager();
        logManager.setLogStrategy(new ConsuoleLogStrategy());
        logManager.info("用户登入");
        /**
         * 也可以使用工厂获取策略
         */
        logManager.setLogStrategy(StrategyFactory.getLogStrategy("dev"));
        logManager.info("切换策略");
    }


}
