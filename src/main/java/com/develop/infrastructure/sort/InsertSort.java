package com.develop.infrastructure.sort;

import java.util.Arrays;

/**
 * @author : jinxiaobo
 * @date : 2026年05月06日 23:06:57
 * @description : 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {12,11,13,5,6};
        int[] arr1 = arr.clone();
        sort(arr1);
        System.out.println(Arrays.toString(arr1));
        int[] arr2 = arr.clone();
        sortWithBinarySearch(arr2);
        System.out.println(Arrays.toString(arr2));
    }


    public static void sort(int[] arr){
        if (arr == null || arr.length <= 1){
            return;
        }
        // 获取数组长度
        int n = arr.length;
        /**
         * 将数组分为两部分
         *      已排序不分,默认是0属于已排序,从1的位置开始遍历
         *      未排序不分
         */
        for (int i = 1; i < n; i++) {
            // 获取到当前值
            int currentValue = arr[i];
            /**
             * 给当前值找一个合适位置进行插入
             * 遍历已排序部分,判断当前值是否小于已经排序的部分，如果小于则进行后移动
             */
            int j = i - 1;
            // 向后移动
            while (j >= 0 && arr[j] > currentValue){
                /**
                 * i = j+1,所以第一次的时候arr[i] = arr[j],i的值被覆盖了
                 * 后面一次类推进行数据的向后移动
                 * 最后j计算找到j的位置,将最开始保留的arr[i] = currentValue进行赋值
                 */
                arr[j+1] = arr[j];
                j--;
            }
            // 给j所在的位置进行赋值
            arr[j+1] = currentValue;
        }
    }


    /**
     * 使用二分查找加快索引定位
     */
    public static void sortWithBinarySearch(int[] arr){
        if (arr == null || arr.length <=1){
            return;
        }
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int currentValue = arr[i];
            // 使用二分查找计算待插入的位置
            int insertIndex = binarySearch(arr, currentValue, 0, i - 1);
            /**
             * 因为insertIndex每次都是计算出来的,有且仅当（j > insertIndex）这个条件说明只要保证当前j到待插入位置insertIndex这段区间的数据向后移动即可
             * 当i=3的时候， j = 3,insertIndex = 0;
             * 那么3之前的所有元素,已经排过序的（11,12,13）都需要向后移动, 因为第一次 arr[j] = 5,所以5的位置会被覆盖为arr[j-1] = 13
             * 以此类推,当j=0的时候,触发条件失败(j > insertIndex),最后将arr[insertIndex] = currentValue; currentValue就是被覆盖掉的5
             */
            for (int j = i;j > insertIndex;j--){
                arr[j] = arr[j-1];
            }
            arr[insertIndex] = currentValue;
        }

    }

    /**
     * 从数组中找到对应的插入位置,找不到则返回左边的
     */
    public static int binarySearch(int[] arr,int key,int low,int high){
        while (low <= high){
            int mid = low + (high - low)/2;
            if (key < arr[mid]){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }

}
