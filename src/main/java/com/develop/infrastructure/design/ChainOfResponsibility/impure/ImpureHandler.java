package com.develop.infrastructure.design.ChainOfResponsibility.impure;

/**
 * 抽象处理者（不纯责任链）
 *
 * 不纯责任链模式的核心特征：
 *   - 一个请求可以被链上的多个处理者依次处理
 *   - 每个处理者处理自己关心的部分，然后将请求继续传递
 *   - 处理者可以在处理前后增加额外逻辑（如预处理、后处理）
 *   - 更贴近实际业务场景（如过滤器链、拦截器链、中间件链）
 *
 * 与纯责任链的关键区别：
 *   - 纯责任链：处理者处理请求后不再传递（单一处理）
 *   - 不纯责任链：处理者处理请求后继续传递给下一个处理者（多级处理）
 *
 * 典型应用：
 *   - Servlet Filter Chain
 *   - Spring Interceptor Chain
 *   - OkHttp Interceptor
 *   - 日志处理链（不同级别日志依次处理）
 */
public abstract class ImpureHandler {

    /**
     * 下一个处理者（后继者）
     */
    protected ImpureHandler next;

    /**
     * 设置下一个处理者
     *
     * @param next 下一个处理者
     * @return 返回下一个处理者，便于链式调用
     */
    public ImpureHandler setNext(ImpureHandler next) {
        this.next = next;
        return next;
    }

    /**
     * 处理请求（不纯责任链版本）
     *
     * 不纯责任链的核心逻辑：
     *   1. 先执行自己的前置处理逻辑
     *   2. 调用 handleCore() 执行核心处理
     *   3. 将请求传递给下一个处理者（无论自己是否处理了该请求）
     *   4. 执行自己的后置处理逻辑
     *
     * @param request  请求对象
     * @param response 响应对象（用于收集所有处理者的处理结果）
     */
    public abstract void handleRequest(String request, StringBuilder response);

    /**
     * 获取处理者名称（用于日志输出）
     */
    protected abstract String getName();
}

