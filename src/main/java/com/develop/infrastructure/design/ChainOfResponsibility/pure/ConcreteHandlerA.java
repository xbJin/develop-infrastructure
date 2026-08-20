package com.develop.infrastructure.design.ChainOfResponsibility.pure;

/**
 * 具体处理者A
 *
 * 职责：处理A类型的请求
 * 纯责任链规则：只能处理自己负责的请求，否则传递给下一个处理者
 */
public class ConcreteHandlerA extends Handler {

    @Override
    public String handleRequest(String request) {
        if ("A".equals(request)) {
            // 自己能处理，直接处理，不再传递
            return "ConcreteHandlerA 处理了请求: " + request;
        }
        // 自己不能处理，传递给下一个处理者
        if (next != null) {
            return next.handleRequest(request);
        }
        // 链中没有处理者能处理该请求
        return "没有任何处理者能处理请求: " + request;
    }
}
