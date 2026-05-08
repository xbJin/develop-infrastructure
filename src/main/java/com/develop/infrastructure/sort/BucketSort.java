package com.develop.infrastructure.sort;

import java.util.Arrays;

/**
 * 桶排序 (Bucket Sort) - 优化版本
 *
 * 算法原理：
 * 桶排序是计数排序的改进版本，它将数据分到有限数量的桶中，
 * 每个桶再分别进行排序（可以使用其他排序算法或递归调用桶排序）。
 * 最后将所有桶中的数据依次取出，组成有序序列。
 *
 * 优化点：
 * 1. 智能选择桶数量 (√n)
 * 2. 小桶使用插入排序，大桶使用快速排序
 * 3. 使用数组而非ArrayList减少内存开销
 * 4. 更健壮的边界处理
 *
 * 时间复杂度：O(n + k) - 平均情况（数据均匀分布）
 * 空间复杂度：O(n + k)
 * 稳定性：稳定（使用插入排序时）
 */
public class BucketSort {

    // 小数组阈值，小于该值使用插入排序
    private static final int INSERTION_SORT_THRESHOLD = 10;
    
    /**
     * 优化版桶排序 - 整数数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        
        // 找到最大值和最小值
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }

        // 如果所有元素相同，直接返回
        if (min == max) {
            return;
        }

        // 智能选择桶数量：通常 √n 是最优选择
        int bucketCount = Math.max(1, (int) Math.sqrt(n));
        
        // 创建桶 - 使用数组避免ArrayList的动态扩容开销
        int[][] buckets = new int[bucketCount][];
        int[] bucketSizes = new int[bucketCount];
        int[] bucketPointers = new int[bucketCount];

        // 计算范围，注意处理整数除法的精度问题
        long range = (long) max - min + 1;
        
        // 第一次遍历：计算每个桶的大小
        for (int value : arr) {
            int bucketIndex = getBucketIndex(value, min, range, bucketCount);
            bucketSizes[bucketIndex]++;
        }

        // 初始化桶数组
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSizes[i] > 0) {
                buckets[i] = new int[bucketSizes[i]];
            }
        }

        // 第二次遍历：将元素放入对应桶中
        for (int value : arr) {
            int bucketIndex = getBucketIndex(value, min, range, bucketCount);
            buckets[bucketIndex][bucketPointers[bucketIndex]++] = value;
        }

        // 对每个桶排序并合并回原数组
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSizes[i] <= 0) continue;
            
            // 根据桶大小选择排序算法
            if (bucketSizes[i] <= INSERTION_SORT_THRESHOLD) {
                insertionSort(buckets[i], 0, bucketSizes[i]);
            } else {
                Arrays.sort(buckets[i], 0, bucketSizes[i]);
            }
            
            // 合并结果
            for (int j = 0; j < bucketSizes[i]; j++) {
                arr[index++] = buckets[i][j];
            }
        }
    }

    /**
     * 获取元素应该放入的桶索引
     */
    private static int getBucketIndex(int value, int min, long range, int bucketCount) {
        // 使用 long 避免整数溢出
        long bucketIndex = ((long) (value - min) * bucketCount) / range;
        // 确保索引在有效范围内
        return (int) Math.min(bucketIndex, bucketCount - 1);
    }

    /**
     * 插入排序 - 对小数组更高效
     */
    private static void insertionSort(int[] arr, int fromIndex, int toIndex) {
        for (int i = fromIndex + 1; i < toIndex; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= fromIndex && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * 优化版桶排序 - 浮点数数组
     */
    public static void sort(double[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        
        // 找到最大值和最小值
        double min = arr[0];
        double max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }

        if (min == max) {
            return;
        }

        int bucketCount = Math.max(1, (int) Math.sqrt(n));
        double range = max - min;
        
        // 使用二维数组存储桶
        double[][] buckets = new double[bucketCount][];
        int[] bucketSizes = new int[bucketCount];
        int[] bucketPointers = new int[bucketCount];

        // 计算桶大小
        for (double value : arr) {
            int bucketIndex = (int) ((value - min) * bucketCount / range);
            bucketIndex = Math.min(bucketIndex, bucketCount - 1);
            bucketSizes[bucketIndex]++;
        }

        // 初始化桶
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSizes[i] > 0) {
                buckets[i] = new double[bucketSizes[i]];
            }
        }

        // 分配元素到桶
        for (double value : arr) {
            int bucketIndex = (int) ((value - min) * bucketCount / range);
            bucketIndex = Math.min(bucketIndex, bucketCount - 1);
            buckets[bucketIndex][bucketPointers[bucketIndex]++] = value;
        }

        // 排序并合并
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSizes[i] <= 0) continue;
            
            if (bucketSizes[i] <= INSERTION_SORT_THRESHOLD) {
                insertionSort(buckets[i], 0, bucketSizes[i]);
            } else {
                Arrays.sort(buckets[i], 0, bucketSizes[i]);
            }
            
            for (int j = 0; j < bucketSizes[i]; j++) {
                arr[index++] = buckets[i][j];
            }
        }
    }

    /**
     * 浮点数插入排序
     */
    private static void insertionSort(double[] arr, int fromIndex, int toIndex) {
        for (int i = fromIndex + 1; i < toIndex; i++) {
            double key = arr[i];
            int j = i - 1;
            while (j >= fromIndex && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * 计数排序 - 适用于小范围整数（保持原功能）
     */
    public static void countingSort(int[] arr, int min, int max) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int range = max - min + 1;
        if (range <= 0) return; // 防止负数范围
        
        int[] count = new int[range];
        
        for (int value : arr) {
            if (value >= min && value <= max) {
                count[value - min]++;
            }
        }

        int index = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                arr[index++] = i + min;
                count[i]--;
            }
        }
    }

    // 测试方法
    public static void main(String[] args) {
        // 测试整数排序
        int[] arr = {4, 1, 5, 9, 2, 7, 3, 6, 8, 0, 10, 15, 13};
        System.out.println("原始数组: " + Arrays.toString(arr));
        
        int[] arr1 = arr.clone();
        sort(arr1);
        System.out.println("优化桶排序: " + Arrays.toString(arr1));

        // 测试浮点数排序
        double[] arr2 = {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68};
        System.out.println("\n浮点数原始数组: " + Arrays.toString(arr2));
        sort(arr2);
        System.out.println("浮点数桶排序: " + Arrays.toString(arr2));

        // 测试计数排序
        int[] arr3 = {2, 5, 3, 1, 4, 2, 3, 5, 1, 4};
        System.out.println("\n小范围整数原始数组: " + Arrays.toString(arr3));
        countingSort(arr3, 1, 5);
        System.out.println("计数排序: " + Arrays.toString(arr3));
        
        // 性能测试示例
        performanceTest();
    }
    
    /**
     * 简单性能测试
     */
    private static void performanceTest() {
        System.out.println("\n=== 性能测试 ===");
        int[] largeArr = new int[100000];
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = (int) (Math.random() * 100000);
        }
        
        // 测试优化版桶排序
        int[] testArr1 = largeArr.clone();
        long start1 = System.nanoTime();
        sort(testArr1);
        long time1 = System.nanoTime() - start1;
        System.out.println("优化桶排序耗时: " + time1 / 1_000_000 + " ms");
        
        // 测试直接Arrays.sort
        int[] testArr2 = largeArr.clone();
        long start2 = System.nanoTime();
        Arrays.sort(testArr2);
        long time2 = System.nanoTime() - start2;
        System.out.println("Arrays.sort耗时: " + time2 / 1_000_000 + " ms");
        
        // 验证结果正确性
        boolean correct = Arrays.equals(testArr1, testArr2);
        System.out.println("结果正确性: " + (correct ? "✓" : "✗"));
    }
}