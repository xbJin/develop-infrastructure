package com.develop.infrastructure.design.ChainOfResponsibility.scenario.filter;

/**
 * CORS跨域过滤器
 *
 * 职责：处理跨域请求
 * 典型场景：前后端分离架构下的跨域访问控制
 */
public class CorsFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // 前置处理：处理预检请求
        System.out.println("[CorsFilter] 前置处理: 检查CORS跨域请求");
        request.addHeader("cors=allowed");

        // 传递给下一个过滤器
        chain.doFilter(request, response);

        // 后置处理：设置CORS响应头
        System.out.println("[CorsFilter] 后置处理: 设置CORS响应头");
        response.addHeader("Access-Control-Allow-Origin: *");
    }
}