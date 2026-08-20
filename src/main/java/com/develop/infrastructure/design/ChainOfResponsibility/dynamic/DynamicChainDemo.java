package com.develop.infrastructure.design.ChainOfResponsibility.dynamic;

/**
 * 动态责任链模式演示
 *
 * 演示内容：
 * 1. 动态构建责任链
 * 2. 动态调整链的顺序（前移、后移、交换）
 * 3. 动态插入和删除处理者
 * 4. 按优先级排序
 * 5. 禁用/启用处理者
 */
public class DynamicChainDemo {

    public static void main(String[] args) {
        System.out.println("===== 动态责任链模式演示 =====\n");

        // ========== 1. 基本动态构建 ==========
        System.out.println("--- 1. 基本动态构建 ---");
        ChainManager manager = new ChainManager();
        manager.addLast(new SimpleHandler("Auth", 1))
               .addLast(new SimpleHandler("RateLimit", 2))
               .addLast(new SimpleHandler("Log", 3))
               .addLast(new SimpleHandler("Business", 4));
        manager.printChainOrder();
        System.out.println(manager.execute("request-1"));

        // ========== 2. 动态调整顺序：前移 ==========
        System.out.println("--- 2. 将 Log 前移一位 ---");
        manager.moveUp("Log");
        manager.printChainOrder();
        System.out.println(manager.execute("request-2"));

        // ========== 3. 动态调整顺序：交换 ==========
        System.out.println("--- 3. 交换 Auth 和 Business 的位置 ---");
        manager.swap("Auth", "Business");
        manager.printChainOrder();
        System.out.println(manager.execute("request-3"));

        // ========== 4. 动态插入 ==========
        System.out.println("--- 4. 在 RateLimit 之前插入 Cache ---");
        manager.addBefore("RateLimit", new SimpleHandler("Cache", 0));
        manager.printChainOrder();
        System.out.println(manager.execute("request-4"));

        // ========== 5. 动态删除 ==========
        System.out.println("--- 5. 删除 Cache 处理者 ---");
        manager.remove("Cache");
        manager.printChainOrder();
        System.out.println(manager.execute("request-5"));

        // ========== 6. 按优先级排序 ==========
        System.out.println("--- 6. 按优先级排序（恢复原始顺序） ---");
        manager.sortByPriority();
        manager.printChainOrder();
        System.out.println(manager.execute("request-6"));

        // ========== 7. 在头部和尾部添加 ==========
        System.out.println("--- 7. 在头部添加 Trace，尾部添加 Metrics ---");
        manager.addFirst(new SimpleHandler("Trace", 0))
               .addLast(new SimpleHandler("Metrics", 99));
        manager.printChainOrder();
        System.out.println(manager.execute("request-7"));

        System.out.println("===== 动态责任链模式演示结束 =====");
    }

    /**
     * 简单处理者实现（用于演示动态调整）
     */
    static class SimpleHandler implements DynamicHandler {

        private final String name;
        private final int priority;
        private boolean enabled = true;

        SimpleHandler(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void handle(String request, DynamicChain chain) {
            chain.getResult().append("[").append(name).append("] 处理请求: ").append(request)
                  .append(" (优先级=").append(priority).append(")\n");
            chain.proceed(request);
        }

        @Override
        public int getPriority() {
            return priority;
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }

        @SuppressWarnings("unused")
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}