package com.develop.infrastructure.middle.thread;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * AtomicInteger + CAS
 * 无锁
 * 自旋消耗 CPU，适合低延迟场景
 */
public class PrintABCWithCAS {

    private static final AtomicInteger state = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(() -> print(0, 'A')).start();
        new Thread(() -> print(1, 'B')).start();
        new Thread(() -> print(2, 'C')).start();
    }

    private static void print(int target, char c) {
        int count = 0;
        while (count < 10) {
            int current = state.get();
            if (current % 3 == target) {
                // CAS 保证只有一个线程成功
                if (state.compareAndSet(current, current + 1)) {
                    System.out.print(c + " ");
                    count++;
                }
            }
        }
    }
}