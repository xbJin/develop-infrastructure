package com.develop.infrastructure.middle.thread;

/**
 * synchronized + wait/notify
 * 简单经典
 * 会惊群（notifyAll）
 */
public class PrintABCWithSync {

    // 当前要打印的字符：0=A,1=B,2=C
    private static int state = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // 打印 A
        new Thread(() -> print(0, 'A')).start();
        // 打印 B
        new Thread(() -> print(1, 'B')).start();
        // 打印 C
        new Thread(() -> print(2, 'C')).start();
    }

    private static void print(int targetState, char c) {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                try {
                    // 不是自己的轮次就等待
                    while (state % 3 != targetState) {
                        lock.wait();
                    }
                    // 打印
                    System.out.print(c + " ");
                    // 切换状态
                    state++;
                    // 唤醒所有线程竞争下一次
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}