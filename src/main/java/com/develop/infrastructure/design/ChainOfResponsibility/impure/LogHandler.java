package com.develop.infrastructure.design.ChainOfResponsibility.impure;

/**
 * 日志处理者
 *
 * 职责：记录请求日志（访问日志、审计日志）
 * 不纯责任链特征：日志记录是附加行为，不影响请求继续传递
 */
public class LogHandler extends ImpureHandler {

    @Override
    public void handleRequest(String request, StringBuilder response) {
        // 前置处理
        response.append("[").append(getName()).append("] 前置处理: 记录请求开始日志\n");

        // 核心处理逻辑
        response.append("[").append(getName()).append("] 日志记录: 请求内容 = ").append(request).append("\n");

        // 继续传递给下一个处理者
        if (next != null) {
            next.handleRequest(request, response);
        }

        // 后置处理
        response.append("[").append(getName()).append("] 后置处理: 记录请求结束日志\n");
    }

    @Override
    protected String getName() {
        return "LogHandler";
    }
}
