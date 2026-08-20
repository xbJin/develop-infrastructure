package com.develop.infrastructure.design.ChainOfResponsibility.scenario.filter;

/**
 * 编码过滤器
 *
 * 职责：设置请求和响应的字符编码
 * 典型场景：解决中文乱码问题
 */
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // 前置处理：设置请求编码
        System.out.println("[EncodingFilter] 前置处理: 设置请求编码为UTF-8");
        request.addHeader("charset=UTF-8");

        // 传递给下一个过滤器
        chain.doFilter(request, response);

        // 后置处理：设置响应编码
        System.out.println("[EncodingFilter] 后置处理: 设置响应编码为UTF-8");
        response.addHeader("Content-Type: text/html;charset=UTF-8");
    }
}
