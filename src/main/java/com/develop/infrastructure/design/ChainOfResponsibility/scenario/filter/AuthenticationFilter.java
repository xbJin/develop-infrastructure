package com.develop.infrastructure.design.ChainOfResponsibility.scenario.filter;

/**
 * 认证过滤器
 *
 * 职责：检查用户是否已登录
 * 典型场景：Session校验、Token验证
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // 前置处理：检查认证信息
        System.out.println("[AuthenticationFilter] 前置处理: 检查用户认证信息");
        request.addHeader("auth=checked");

        // 传递给下一个过滤器
        chain.doFilter(request, response);

        // 后置处理
        System.out.println("[AuthenticationFilter] 后置处理: 认证流程完成");
    }
}
