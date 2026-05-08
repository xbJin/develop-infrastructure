package com.develop.infrastructure.sort;

import java.util.Arrays;

/**
 * @author : jinxiaobo
 * @date : 2026年04月22日 23:27:49
 * @description :
 */
public class BubbleSort {

    /**
     * 冒泡排序
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("原始数组: " + Arrays.toString(arr));

        int[] arr1 = arr.clone();
        bubbleSort(arr1);
        System.out.println("基础冒泡排序: " + Arrays.toString(arr1));

        int[] arr2 = arr.clone();
        sortOptimized(arr2);
        System.out.println("优化版本冒泡排序: " + Arrays.toString(arr2));

        int[] arr3 = arr.clone();
        cocktailSort(arr3);
        System.out.println("优化版本冒泡排序: " + Arrays.toString(arr3));
    }

    /**
     * 基础冒泡排序
     * @param nums
     */
    public static void bubbleSort(int[] nums){
        if (nums == null || nums.length <= 1){
            return;
        }
        // 数组长度
        int n = nums.length;
        boolean swapped;
        /**
         * 外层循环,控制排序的轮数
         */
        for (int i = 0; i < n; i++) {
            swapped = false;
            // 内层循环,执行比较和交换,每次将最大的元素冒泡到数组的末尾
            for (int j = 0; j < n -i -1; j++) {
                if (nums[j] > nums[j+1]){
                    // 前一个元素比后一个元素大,执行交换
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    swapped = true;
                }
            }
            /**
             * 如果在这一轮中没有发生交换,说明数组已经有序,直接退出循环
             * 没有交换说明数组已经有序,不需要继续排序
             */
            if (!swapped){
                break;
            }
        }
    }


    /**
     * 优化版冒泡排序
     */
    public static void sortOptimized(int[] nums){
        if(nums == null || nums.length <= 1){
            return;
        }
        int n = nums.length;
        // 最后一次交换的位置,默认是数组的最后一个位置
        int lastSwapped = n - 1;
        /**
         * 只要交换位置大于0,说明数组还没有有序,需要继续排序
         */
        while (lastSwapped > 0){
            for (int i = 0; i < n; i++) {
                int currentLastSwap = 0;
                for (int j = 0; j < n - 1 - i; j++) {
                    if (nums[j] > nums[j+1]){
                        // 前一个元素比后一个元素大,执行交换
                        int temp = nums[j];
                        nums[j] = nums[j+1];
                        nums[j+1] = temp;
                        currentLastSwap = j;
                    }
                }
                /**
                 * 每次记录的其实是当前轮最后一次交换的位置
                 * 直到交换到0,说明数组已经有序,不需要继续排序
                 */
                lastSwapped = currentLastSwap;
            }
        }
    }


    /**
     * 双向冒泡排序，这段代码也能实现，但是有冗余操作
     */
    /*public static void cocktailSort(int[] nums){
        if (nums == null || nums.length <= 1){
            return;
        }
        int n= nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right){
            for (int i = 0; i < n; i++) {
                //先放右边最大值
                for (int j = 0; j < n - 1 - i; j++) {
                    if (nums[j] > nums[j + 1]){
                        int temp = nums[j];
                        nums[j] = nums[j+1];
                        nums[j+1] = temp;
                        // 重置right
                        right = j;
                    }
                }
                // 再放左边最小值
                for (int j = n - 1 - i; j > 0; j--) {
                    // 后面的元素比前面的元素小,执行交换
                    if (nums[j] < nums[j - 1]){
                        int temp = nums[j];
                        nums[j] = nums[j-1];
                        nums[j-1] = temp;
                        // 重置left
                        left = j;
                    }
                }
            }
        }
    }*/

    /**
     * 双向冒泡
     * @param arr
     */
    public static void cocktailSort(int[] arr){
        if (arr == null || arr.length <= 1){
            return;
        }
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        int lastSwapIndex = 0;

        while (left < right) {
            // 正向冒泡：将最大元素移到右边
            for (int i = left; i < right; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    lastSwapIndex = i;
                }
            }
            right = lastSwapIndex;

            /**
             * 不需要进行反向冒泡了
             */
            if (left >= right) {
                break;
            }

            /**
             * 反向冒泡：将最小元素移到左边
             * 根据right的位置反着来冒泡
             */
            for (int i = right; i > left; i--) {
                if (arr[i] < arr[i - 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    lastSwapIndex = i;
                }
            }
            left = lastSwapIndex;
        }
    }



}
