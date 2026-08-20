package com.develop.infrastructure.design.ChainOfResponsibility.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态责任链上下文
 *
 * 职责：
 *   1. 维护处理者列表的遍历游标
 *   2. 提供 proceed() 方法将请求传递给下一个处理者
 *   3. 支持在遍历过程中跳过被禁用的处理者
 *
 * 设计思路：
 *   - 参考了 Servlet FilterChain 和 OkHttp Interceptor Chain 的设计
 *   - 通过游标 index 控制请求在链中的流转
 *   - 每次调用 proceed() 时，index 递增，指向下一个处理者
 */
public class DynamicChain {

    /** 处理者列表 */
    private final List<DynamicHandler> handlers;

    /** 当前游标位置 */
    private int index;

    /** 请求处理结果收集器 */
    private final StringBuilder result;

    /**
     * 构造动态责任链
     *
     * @param handlers 处理者列表（按顺序排列）
     * @param result   结果收集器
     */
    public DynamicChain(List<DynamicHandler> handlers, StringBuilder result) {
        this.handlers = new ArrayList<>(handlers);
        this.index = 0;
        this.result = result;
    }

    /**
     * 将请求传递给下一个处理者
     *
     * 核心流程：
     *   1. 检查游标是否超出链的长度（链已遍历完毕）
     *   2. 跳过被禁用的处理者
     *   3. 调用当前处理者的 handle() 方法
     *   4. 处理者内部可以选择是否调用 chain.proceed() 来继续传递
     */
    public void proceed(String request) {
        // 跳过被禁用的处理者
        while (index < handlers.size() && !handlers.get(index).isEnabled()) {
            result.append("[Chain] 跳过已禁用的处理者: ").append(handlers.get(index).getName()).append("\n");
            index++;
        }

        if (index < handlers.size()) {
            DynamicHandler handler = handlers.get(index);
            index++;
            handler.handle(request, this);
        } else {
            result.append("[Chain] 责任链处理完毕\n");
        }
    }

    /**
     * 获取结果收集器
     */
    public StringBuilder getResult() {
        return result;
    }
}