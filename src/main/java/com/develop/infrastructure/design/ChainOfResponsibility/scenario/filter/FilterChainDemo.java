package com.develop.infrastructure.design.ChainOfResponsibility.scenario.filter;

/**
 * 过滤器链演示（不纯责任链应用场景 - Servlet Filter Chain）
 *
 * 过滤器链的特点：
 *   - 请求从第一个过滤器开始，依次通过所有过滤器到达目标资源
 *   - 响应从目标资源返回，逆序通过所有过滤器的后置处理
 *   - 每个过滤器都可以在请求前后添加处理逻辑
 *   - 过滤器可以选择中断链（不调用chain.doFilter()）
 *
 * 这是不纯责任链的典型实现，参考了 Servlet Filter 的设计模式
 */
public class FilterChainDemo {

    public static void main(String[] args) {
        System.out.println("===== 过滤器链演示（Servlet Filter Chain场景） =====\n");

        // 1. 创建过滤器链
        FilterChain filterChain = new FilterChain("UserController/getUser");

        // 2. 添加过滤器（按执行顺序）
        filterChain.addFilter(new CorsFilter())
                   .addFilter(new EncodingFilter())
                   .addFilter(new AuthenticationFilter());

        System.out.println("过滤器链：CorsFilter -> EncodingFilter -> AuthenticationFilter -> Target\n");

        // 3. 模拟请求处理
        System.out.println("--- 模拟请求处理流程 ---");
        Request request = new Request("/api/user/123", "{userId: 123}");
        Response response = new Response();

        System.out.println(">>> 请求进入过滤器链...");
        filterChain.doFilter(request, response);
        System.out.println(">>> 响应返回客户端");

        // 4. 打印处理结果
        System.out.println("\n--- 请求信息 ---");
        System.out.println("请求路径: " + request.getPath());
        System.out.println("请求头: " + request.getHeaders());
        System.out.println("请求体: " + request.getBody());

        System.out.println("\n--- 响应信息 ---");
        System.out.println("响应状态: " + response.getStatus());
        System.out.println("响应头: " + response.getHeaders());
        System.out.println("响应体: " + response.getBody());

        System.out.println("\n===== 过滤器链演示结束 =====");
    }
}
