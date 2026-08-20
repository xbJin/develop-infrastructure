package com.develop.infrastructure.sort;

import java.util.Arrays;

/**
 * 选择排序 (Selection Sort)
 *
 * 算法原理：
 * 首先在未排序序列中找到最小（或最大）元素，放到排序序列的起始位置，
 * 然后从剩余未排序元素中继续寻找最小（或最大）元素，放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 *
 * 时间复杂度：O(n²) - 无论最好还是最坏情况都是O(n²)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class SelectionSort {

    /**
     * 基础选择排序
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // 找到未排序部分的最小元素索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 将最小元素交换到已排序部分的末尾，本质是当前i的值和minIndex的值交换
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * 双向选择排序 - 同时选择最大值和最小值
     */
    public static void doubleSelectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int minIndex = left;
            int maxIndex = right;

            for (int i = left; i <= right; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }

            // 将最小元素交换到左边
            if (minIndex != left) {
                int temp = arr[left];
                arr[left] = arr[minIndex];
                arr[minIndex] = temp;
            }

            // 如果最大值在left位置（已被交换），更新maxIndex
            if (maxIndex == left) {
                maxIndex = minIndex;
            }

            // 将最大元素交换到右边
            if (maxIndex != right) {
                int temp = arr[right];
                arr[right] = arr[maxIndex];
                arr[maxIndex] = temp;
            }

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("原始数组: " + Arrays.toString(arr));

        int[] arr1 = arr.clone();
        sort(arr1);
        System.out.println("选择排序: " + Arrays.toString(arr1));

        int[] arr2 = arr.clone();
        doubleSelectionSort(arr2);
        System.out.println("双向选择排序: " + Arrays.toString(arr2));
    }
}