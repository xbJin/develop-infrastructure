package com.develop.infrastructure.leetcode.leet100;

import java.util.Arrays;

/**
 * @author : jinxiaobo
 * @date : 2026年04月19日 13:28:32
 * @description :
 */
public class LeetCode081TO090 {

    public static void main(String[] args) {
        /**
         * 合并两个有序数组 ： 88题
         */
        int[] resultArr = mergeTwoSortArr(new int[]{1, 3, 5, 7, 9}, 5, new int[]{2, 4, 6, 8, 10}, 5);
        System.out.println(Arrays.toString(resultArr));
    }

    /**
     * 合并两个有序数组
     * @return
     */
    public static int[] mergeTwoSortArr(int[] nums1,int m,int[] nums2,int n){
        // 定义返回结果数组
        int[] resultArr = new int[m+n];
        /**
         * 定义移动指针
         *  x : 对应i数组的移动
         *  y : 对应j数组的移动
         * 定义容器指针： k 用于记录resultArr当前元素的个数
         */
        int i = 0,j = 0,k =0;
        while (i < m && j < n){
            if (nums1[i]<nums2[i]){
                resultArr[k++] = nums1[i++];
            }else {
                resultArr[k++] = nums2[j++];
            }
        }
        // 将i之后的元素添加到数组中
        while (i < nums1.length){
            resultArr[k++] = nums1[i++];
        }

        while (j < nums2.length){
            resultArr[k++] = nums2[j++];
        }

        return resultArr;
    }



}
