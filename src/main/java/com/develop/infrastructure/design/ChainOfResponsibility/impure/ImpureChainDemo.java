package com.develop.infrastructure.design.ChainOfResponsibility.impure;

/**
 * 不纯责任链模式演示
 *
 * 不纯责任链的核心流程：
 * 1. 创建多个具体处理者
 * 2. 通过 setNext() 方法将处理者串成一条链
 * 3. 请求从链头开始，每个处理者都执行自己的处理逻辑
 * 4. 每个处理者处理完后，将请求继续传递给下一个处理者
 * 5. 所有处理者都有机会处理该请求
 *
 * 与纯责任链的对比：
 *   - 纯责任链：请求被一个处理者处理后停止传递
 *   - 不纯责任链：请求被所有处理者依次处理
 *
 * 本例模拟一个Web请求处理链：认证 -> 限流 -> 日志 -> 业务处理
 */
public class ImpureChainDemo {

    public static void main(String[] args) {
        System.out.println("===== 不纯责任链模式（Impure Chain）演示 =====\n");

        // 1. 创建处理者
        ImpureHandler authHandler = new AuthHandler();
        ImpureHandler rateLimitHandler = new RateLimitHandler();
        ImpureHandler logHandler = new LogHandler();
        ImpureHandler businessHandler = new BusinessHandler();

        // 2. 组装责任链：认证 -> 限流 -> 日志 -> 业务处理
        authHandler.setNext(rateLimitHandler).setNext(logHandler).setNext(businessHandler);

        System.out.println("责任链组装完成：Auth -> RateLimit -> Log -> Business\n");

        // 3. 模拟正常请求
        System.out.println("--- 场景1：正常请求（带有效token） ---");
        StringBuilder response1 = new StringBuilder();
        authHandler.handleRequest("token=valid&action=query", response1);
        System.out.println(response1);

        // 4. 模拟异常请求（无token + 突发流量）
        System.out.println("--- 场景2：异常请求（无token + 突发流量） ---");
        StringBuilder response2 = new StringBuilder();
        authHandler.handleRequest("action=delete&burst", response2);
        System.out.println(response2);

        System.out.println("===== 不纯责任链模式演示结束 =====");
    }
}
