package com.develop.infrastructure.middle.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock + Condition
 * 精准唤醒
 * 性能最好，面试最爱
 */
public class PrintABCWithLock {

    private static int state = 0;
    private static final Lock lock = new ReentrantLock();
    private static final Condition condA = lock.newCondition();
    private static final Condition condB = lock.newCondition();
    private static final Condition condC = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> printA()).start();
        new Thread(() -> printB()).start();
        new Thread(() -> printC()).start();
    }

    private static void printA() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (state % 3 != 0) condA.await();
                System.out.print("A ");
                state++;
                condB.signal(); // 只唤醒 B
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    private static void printB() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (state % 3 != 1) condB.await();
                System.out.print("B ");
                state++;
                condC.signal(); // 只唤醒 C
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    private static void printC() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (state % 3 != 2) condC.await();
                System.out.print("C ");
                state++;
                condA.signal(); // 只唤醒 A
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}