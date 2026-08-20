package com.develop.infrastructure.design.ChainOfResponsibility.pure;

/**
 * 具体处理者C
 *
 * 职责：处理C类型的请求
 * 纯责任链规则：只能处理自己负责的请求，否则传递给下一个处理者
 */
public class ConcreteHandlerC extends Handler {

    @Override
    public String handleRequest(String request) {
        if ("C".equals(request)) {
            return "ConcreteHandlerC 处理了请求: " + request;
        }
        if (next != null) {
            return next.handleRequest(request);
        }
        return "没有任何处理者能处理请求: " + request;
    }
}