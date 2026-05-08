package com.develop.infrastructure.design.Strategy.log;

import java.time.LocalDateTime;

/**
 * @author : jinxiaobo
 * @date : 2026年05月08日 23:31:31
 * @description :
 */
public class ConsuoleLogStrategy implements LogStrategy{
    @Override
    public void log(String message, String level) {
        String msg = String.format("[%s] %s %s", LocalDateTime.now(), message, level);
        System.out.println(msg);
    }
}
