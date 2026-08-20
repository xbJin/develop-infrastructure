package com.develop.infrastructure.middle.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 基础通用的思路
 */
public class ThreadPrintAB {

    // 原子计数器，从 0 开始（控制顺序）
    private static AtomicLong count = new AtomicLong(0);

    // 固定 3 个线程的线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        threadPrint();
    }

    /**
     * 启动 30 个任务，循环打印 A、B
     * 规则：
     * 偶数 -> 打印 A
     * 奇数 -> 打印 B
     * 每个字母打印 10 次就停止
     */
    public static void threadPrint() {
        // 提交 30 个任务
        for (int i = 0; i < 30; i++) {
            executorService.submit(() -> {
                // 每个线程一直尝试打印，直到打印够次数
                while (true) {
                    // 获取当前计数值（原子操作）
                    long current = count.get();

                    // 控制：A 打印 10 次，B 打印 10 次 → 总共 20 次就结束
                    if (current >= 20) {
                        break;
                    }

                    // CAS：保证只有一个线程能成功修改 count
                    if (count.compareAndSet(current, current + 1)) {
                        // 偶数打印 A
                        if (current % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + " → A");
                        }
                        // 奇数打印 B
                        else {
                            System.out.println(Thread.currentThread().getName() + " → B");
                        }
                    }
                }
            });
        }

        // 关闭线程池
        executorService.shutdown();
    }
}