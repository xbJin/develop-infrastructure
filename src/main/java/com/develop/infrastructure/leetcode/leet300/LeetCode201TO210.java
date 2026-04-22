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
        /**
         * 记录执行过的数字,避免循环计算,进入无限循环
         */
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getDigitSquareSum(n);
        }
        return n == 1;
    }

    /**
     * 精髓就在循环里面每次num % 10拿到余数,就是最后一位数字,进行计算
     * 然后num /= 10,就是去掉最后一位数字，接着执行下一步循环
     * 只要num > 0,就说明还存在可以操作的数字
     * 最后返回sum
     */
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
