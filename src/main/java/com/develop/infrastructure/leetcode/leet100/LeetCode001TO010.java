package com.develop.infrastructure.leetcode.leet100;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author : jinxiaobo
 * @date : 2026年04月12日 17:30:13
 * @description :
 */
public class LeetCode001TO005 {

    public static void main(String[] args) {
        /**
         * 两数相加,hash表
         */
        int[] arr01 = twoSum01(new int[]{2, 4, 6, 7}, 7);
        int[] arr02 = twoSum01(new int[]{2, 4, 6, 7}, 6);
        System.out.println(Arrays.toString(arr01));
        System.out.println(Arrays.toString(arr02));
        /**
         * 两数相加,双重for循环
         */
        int[] arr03 = twoSum02(new int[]{2, 4, 6, 7}, 7);
        int[] arr04 = twoSum02(new int[]{2, 4, 6, 7}, 6);
        System.out.println(Arrays.toString(arr03));
        System.out.println(Arrays.toString(arr04));
    }

    /**
     * 两数相加: 使用哈希表方式实现
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum01(int[] nums,int target){
        /**
         * 定义hash表
         *  key     元素值
         *  value   元素值所在的索引位置
         */
        HashMap<Integer, Integer> existMap = new HashMap<Integer, Integer>();
        /**
         * 遍历元数据
         * 1、判断hash表中是否已经包含了
         * 2、初始化hash表数据
         */
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (existMap.containsKey(diff)){
                return new int[]{i,existMap.get(diff)};
            } else {
                existMap.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 两数相加,使用双重for循环
     * @return
     */
    public static int[] twoSum02(int[] nums,int target){
        /**
         * 遍历所有元素
         * 前面的每次都和他后面的进行相加然后和目标值进行比对
         * 比对成功则返回结果,比对失败继续后移比对
         */
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }





}
