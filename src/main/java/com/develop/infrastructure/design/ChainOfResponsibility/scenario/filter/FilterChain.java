package com.develop.infrastructure.design.ChainOfResponsibility.scenario.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链
 *
 * 核心设计：
 *   - 维护过滤器列表和当前游标位置
 *   - doFilter()方法将请求传递给下一个过滤器
 *   - 当所有过滤器执行完毕后，调用目标资源
 *
 * 这是不纯责任链的典型实现：
 *   - 每个过滤器都可以在请求前后添加处理逻辑
 *   - 请求依次通过所有过滤器
 *   - 过滤器可以选择中断链（不调用chain.doFilter()）
 */
public class FilterChain {

    /** 过滤器列表 */
    private final List<Filter> filters = new ArrayList<>();

    /** 当前游标 */
    private int index = 0;

    /** 目标资源（模拟Servlet） */
    private String target;

    public FilterChain(String target) {
        this.target = target;
    }

    /**
     * 添加过滤器
     */
    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    /**
     * 执行下一个过滤器或目标资源
     *
     * @param request  请求
     * @param response 响应
     */
    public void doFilter(Request request, Response response) {
        if (index < filters.size()) {
            Filter filter = filters.get(index);
            index++;
            // 调用当前过滤器，将自身传递进去以便过滤器调用下一个
            filter.doFilter(request, response, this);
        } else {
            // 所有过滤器执行完毕，调用目标资源
            response.setBody("目标资源[" + target + "]处理完成");
            System.out.println(">>> 到达目标资源: " + target + "，处理请求: " + request.getPath());
        }
    }

    /**
     * 重置过滤器链（用于复用）
     */
    public void reset() {
        index = 0;
    }
}