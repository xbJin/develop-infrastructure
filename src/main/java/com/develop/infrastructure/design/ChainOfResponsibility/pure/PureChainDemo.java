package com.develop.infrastructure.design.ChainOfResponsibility.pure;

import java.util.logging.Handler;

/**
 * 纯责任链模式演示
 * <p>
 * 纯责任链的核心流程：
 * 1. 创建多个具体处理者
 * 2. 通过 setNext() 方法将处理者串成一条链
 * 3. 请求从链头开始，沿链传递，直到被某个处理者处理
 * 4. 一旦被处理，请求不再继续传递
 * <p>
 * 验证要点：
 * - 请求A只会被HandlerA处理，不会传递到HandlerB或HandlerC
 * - 请求D无法被任何处理者处理，返回未处理提示
 * - 每个请求有且仅有一个处理者（或无人处理）
 */
public class PureChainDemo {

    public static void main(String[] args) {
        System.out.println("===== 纯责任链模式（Pure Chain）演示 =====\n");

        // 1. 创建处理者
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        Handler handlerC = new ConcreteHandlerC();

        // 2. 组装责任链：A -> B -> C
        handlerA.setNext(handlerB).setNext(handlerC);

        System.out.println("责任链组装完成：HandlerA -> HandlerB -> HandlerC\n");

        // 3. 发送不同类型的请求
        System.out.println("--- 发送请求 A ---");
        String resultA = handlerA.handleRequest("A");
        System.out.println("结果: " + resultA);

        System.out.println("\n--- 发送请求 B ---");
        String resultB = handlerA.handleRequest("B");
        System.out.println("结果: " + resultB);

        System.out.println("\n--- 发送请求 C ---");
        String resultC = handlerA.handleRequest("C");
        System.out.println("结果: " + resultC);

        System.out.println("\n--- 发送请求 D（无人能处理） ---");
        String resultD = handlerA.handleRequest("D");
        System.out.println("结果: " + resultD);

        System.out.println("\n===== 纯责任链模式演示结束 =====");
    }
}
