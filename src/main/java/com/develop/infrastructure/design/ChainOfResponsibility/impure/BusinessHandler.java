package com.develop.infrastructure.design.ChainOfResponsibility.impure;

/**
 * 业务处理者
 *
 * 职责：执行核心业务逻辑
 * 不纯责任链特征：作为链的末端，执行完业务逻辑后不再传递
 */
public class BusinessHandler extends ImpureHandler {

    @Override
    public void handleRequest(String request, StringBuilder response) {
        // 前置处理
        response.append("[").append(getName()).append("] 前置处理: 准备执行业务逻辑\n");

        // 核心处理逻辑
        response.append("[").append(getName()).append("] 业务处理: 执行核心业务操作 - ").append(request).append("\n");

        // 链末端，没有下一个处理者
        // 后置处理
        response.append("[").append(getName()).append("] 后置处理: 业务执行完毕，返回响应\n");
    }

    @Override
    protected String getName() {
        return "BusinessHandler";
    }
}

