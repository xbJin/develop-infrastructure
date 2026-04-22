package com.develop.infrastructure.search;

/**
 * @author : jinxiaobo
 * @date : 2026年04月23日 00:26:02
 * @description :
 */
public class BinarySearch {


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
        int target = 5;
        int index = binarySearch(arr, target);
        System.out.println(index);
    }


    public static int binarySearch(int[] arr, int target){
        if (arr == null || arr.length <= 0){
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = left + (right - left)/2;
            if (arr[mid] == target){
                return mid;
            } else if (arr[mid] > target){
                // 重置right
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

}
