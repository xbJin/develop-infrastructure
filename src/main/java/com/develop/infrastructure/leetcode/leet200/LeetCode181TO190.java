package com.develop.infrastructure.leetcode.leet200;

import java.util.Arrays;

/**
 * @author : jinxiaobo
 * @date : 2026年04月19日 16:49:46
 * @description :
 */
public class LeetCode181TO190 {


    public static void main(String[] args) {
        /**
         * LeetCode,轮转数组 189
         */
        System.out.println(Arrays.toString(rotate01(new int[]{1,2,3,4,5,6,7}, 3)));
        rotate(new int[]{1,2,3,4,5,6,7}, 3);
    }


    /**
     * 轮转数组 leetcode 189
     * 基础的解法
     */
    public static int[] rotate01(int[] nums, int k) {
        /**
         * 先定义返回结果数组
         */
        int[] result = new int[nums.length];
        /**
         * 记录结果指针索引
         * 截取原来数组的最后k个元素
         */
        int index = k;
        for (int i = nums.length; i > nums.length-k; i--) {
            result[--index] = nums[i-1];
        }
        /**
         * 正向遍历原数组,从k开始,到result.length-1,赋值的为原数组索引为0位置开始的元素
         */
        int numsIndex = 0;
        while (k<=result.length-1) {
            result[k++] = nums[numsIndex++];
        }
        return result;
    }

    /**
     * LeetCode 轮转数组标准解法 189
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        // 如果k为0或数组长度整数倍则无需操作
        if (k == 0) {
            return;
        }
        // 先反转整个数组
        reverse(nums, 0, n - 1);
        // 再反转前k个元素
        reverse(nums, 0, k - 1);
        // 最后反转后n-k个元素
        reverse(nums, k, n - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        // 交换元素
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
