package com.develop.infrastructure.design.ChainOfResponsibility.dynamic;

/**
 * 动态责任链处理者接口
 *
 * 与传统责任链的区别：
 *   - 传统责任链：通过 setNext() 硬编码链的顺序，运行时无法调整
 *   - 动态责任链：通过 ChainManager 管理链的组成和顺序，支持运行时动态调整
 *
 * 动态调整的方式：
 *   1. 添加处理者：addFirst / addLast / addBefore / addAfter
 *   2. 删除处理者：remove
 *   3. 调整顺序：moveUp / moveDown / swap
 *   4. 替换处理者：replace
 *   5. 条件启用/禁用：enable / disable
 */
public interface DynamicHandler {

    /**
     * 获取处理者名称（用于在链中唯一标识）
     *
     * @return 处理者名称
     */
    String getName();

    /**
     * 处理请求
     *
     * @param request 请求内容
     * @param chain   责任链上下文（用于将请求传递给下一个处理者）
     */
    void handle(String request, DynamicChain chain);

    /**
     * 获取处理者优先级（数值越小优先级越高）
     * 默认优先级为 Integer.MAX_VALUE（最低优先级）
     *
     * @return 优先级
     */
    default int getPriority() {
        return Integer.MAX_VALUE;
    }

    /**
     * 处理者是否启用
     * 禁用的处理者将被跳过
     *
     * @return true表示启用，false表示禁用
     */
    default boolean isEnabled() {
        return true;
    }
}