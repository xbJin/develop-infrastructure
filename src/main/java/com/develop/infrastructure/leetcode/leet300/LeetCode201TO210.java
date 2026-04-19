package com.develop.infrastructure.leetcode.leet300;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author : jinxiaobo
 * @date : 2026年04月19日 17:12:26
 * @description :
 */
public class LeetCode201TO210 {


    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));
    }

    /**
     * 判断一个数是不是快乐数 leetcode 202
     * @param n 给定一个数字 n
     * @return 如果 n 是 快乐数 就返回 true ；不是，则返回 false
     */
    public static boolean isHappy(int n) {
        if (Objects.equals(n,1)){
            return true;
        }
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getDigitSquareSum(n);
        }
        return n == 1;
    }

    // 计算数字各位置平方和
    private static int getDigitSquareSum(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

}
