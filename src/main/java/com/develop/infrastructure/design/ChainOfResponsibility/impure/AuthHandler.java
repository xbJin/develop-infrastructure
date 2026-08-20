package com.develop.infrastructure.design.ChainOfResponsibility.impure;

/**
 * 认证处理者
 *
 * 职责：验证用户身份（如Token校验、登录状态检查）
 * 不纯责任链特征：处理完自己的逻辑后，继续将请求传递给下一个处理者
 */
public class AuthHandler extends ImpureHandler {

    @Override
    public void handleRequest(String request, StringBuilder response) {
        // 前置处理
        response.append("[").append(getName()).append("] 前置检查: 验证用户身份...\n");

        // 核心处理逻辑
        if (request.contains("token=valid")) {
            response.append("[").append(getName()).append("] 认证通过: 用户身份验证成功\n");
        } else {
            response.append("[").append(getName()).append("] 认证警告: 用户身份验证失败，但仍继续传递（不纯链特征）\n");
        }

        // 继续传递给下一个处理者（不纯责任链的关键：无论处理结果如何，都继续传递）
        if (next != null) {
            next.handleRequest(request, response);
        }

        // 后置处理
        response.append("[").append(getName()).append("] 后置处理: 记录认证日志\n");
    }

    @Override
    protected String getName() {
        return "AuthHandler";
    }
}
