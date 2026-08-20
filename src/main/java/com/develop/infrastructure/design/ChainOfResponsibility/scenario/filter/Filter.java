package com.develop.infrastructure.design.ChainOfResponsibility.scenario.filter;

/**
 * 过滤器接口（不纯责任链应用 - Web过滤器链）
 *
 * 场景说明：
 *   模拟Servlet Filter Chain，这是不纯责任链最经典的应用之一。
 *   每个Filter在请求到达目标之前进行预处理，在响应返回之后进行后处理。
 *
 * 设计思路：
 *   - 参考javax.servlet.Filter的设计
 *   - doFilter()方法中，Filter可以选择是否将请求传递给下一个Filter
 *   - 通过FilterChain控制请求的流转
 */
public interface Filter {

    /**
     * 执行过滤逻辑
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param chain    过滤器链（用于调用下一个过滤器）
     */
    void doFilter(Request request, Response response, FilterChain chain);
}
