package com.develop.infrastructure.design.ChainOfResponsibility.impure;

/**
 * 限流处理者
 *
 * 职责：检查请求频率，防止接口被恶意刷量
 * 不纯责任链特征：即使触发了限流，也可以选择继续传递（记录日志等）
 */
public class RateLimitHandler extends ImpureHandler {

    @Override
    public void handleRequest(String request, StringBuilder response) {
        // 前置处理
        response.append("[").append(getName()).append("] 前置检查: 检查请求频率...\n");

        // 核心处理逻辑
        if (request.contains("burst")) {
            response.append("[").append(getName()).append("] 限流触发: 请求过于频繁，已记录限流日志\n");
        } else {
            response.append("[").append(getName()).append("] 限流检查通过: 请求频率正常\n");
        }

        // 继续传递给下一个处理者
        if (next != null) {
            next.handleRequest(request, response);
        }

        // 后置处理
        response.append("[").append(getName()).append("] 后置处理: 更新限流计数器\n");
    }

    @Override
    protected String getName() {
        return "RateLimitHandler";
    }
}