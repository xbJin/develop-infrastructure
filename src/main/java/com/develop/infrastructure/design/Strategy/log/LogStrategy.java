package com.develop.infrastructure.design.Strategy.log;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:30:07
 * @description : 日志策略接口
 */
public interface LogStrategy {

    /**
     * 日志接口
     * @param message 日志消息
     * @param level 日志等级
     */
    void log(String message,String level);

}
