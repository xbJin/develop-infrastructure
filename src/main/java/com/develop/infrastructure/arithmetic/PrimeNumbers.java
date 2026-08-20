package com.develop.infrastructure.arithmetic;

public class PrimeNumbers {
    public static void main(String[] args) {
        int count = countPrimes(100);
        System.out.println("100以内的素数个数：" + count);
    }

    /**
     * 统计 n 以内的素数数量（埃氏筛法）
     * @param n 上限数字（这里传 100）
     * @return 素数总数
     */
    public static int countPrimes(int n) {
        // 边界：小于 2 的数没有素数
        if (n < 2) {
            return 0;
        }

        // 1. 创建布尔数组，isPrime[i] 表示数字 i 是否是素数
        // 初始默认全是 false，我们先假设所有数字都不是素数
        boolean[] isPrime = new boolean[n];

        // 2. 先假设所有数都是素数（除了0、1）
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        // 3. 埃氏筛核心：
        // 从 2 开始，把每个素数的倍数都标记为 非素数
        // 优化点：只需遍历到 sqrt(n)，因为超过 sqrt(n) 的数已被前面筛掉
        for (int i = 2; i * i < n; i++) {
            // 如果当前数是素数，就开始筛它的倍数
            if (isPrime[i]) {
                // 把 i 的 2倍、3倍、4倍...全部标记为非素数
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 4. 统计素数数量
        int count = 0;
        for (boolean b : isPrime) {
            if (b) count++;
        }

        return count;
    }


    public static int countPrimesBruteForce(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) count++;
        }
        return count;
    }
}