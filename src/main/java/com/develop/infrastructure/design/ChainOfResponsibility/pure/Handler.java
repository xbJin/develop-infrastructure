package com.develop.infrastructure.design.ChainOfResponsibility.pure;

/**
 * 抽象处理者（纯责任链）
 *
 * 纯责任链模式的核心特征：
 *   - 每个处理者要么完全处理请求，要么将请求传递给下一个处理者
 *   - 不存在一个请求被多个处理者部分处理的情况
 *   - 请求最终只会被链中的一个处理者所处理
 *
 * 对比不纯责任链：
 *   - 纯责任链：一个请求只被一个处理者处理（单一处理原则）
 *   - 不纯责任链：一个请求可以被多个处理者依次处理（多级处理原则）
 */
public abstract class Handler {

    /**
     * 下一个处理者（后继者）
     */
    protected Handler next;

    /**
     * 设置下一个处理者
     *
     * @param next 下一个处理者
     * @return 返回下一个处理者，便于链式调用
     */
    public Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    /**
     * 处理请求
     *
     * 纯责任链的核心逻辑：
     *   1. 判断自己是否能处理该请求
     *   2. 如果能处理，则处理并返回结果（不再传递）
     *   3. 如果不能处理，则传递给下一个处理者
     *
     * @param request 请求对象
     * @return 处理结果，如果没有任何处理者能处理则返回null
     */
    public abstract String handleRequest(String request);
}
