package com.develop.infrastructure.leetcode.leet100;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author : jinxiaobo
 * @date : 2026年04月12日 17:30:13
 * @description :
 */
public class LeetCode001TO010 {

    public static void main(String[] args) {
        /**
         * LeetCode001
         * 两数之和,hash表
         */
        int[] arr01 = twoSum01(new int[]{2, 4, 6, 7}, 7);
        int[] arr02 = twoSum01(new int[]{2, 4, 6, 7}, 6);
        System.out.println(Arrays.toString(arr01));
        System.out.println(Arrays.toString(arr02));
        /**
         * LeetCode001
         * 两数之和,双重for循环
         */
        int[] arr03 = twoSum02(new int[]{2, 4, 6, 7}, 7);
        int[] arr04 = twoSum02(new int[]{2, 4, 6, 7}, 6);
        System.out.println(Arrays.toString(arr03));
        System.out.println(Arrays.toString(arr04));


        /**
         * leetcode002
         * 两数相加，两个链表相加
         */
        // l1: 2 -> 4 -> 3
        // l2: 5 -> 6 -> 4
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode result = addTwoNumbers(l1, l2);
        printList(result); // 应该输出: 7 -> 0 -> 8

        // 测试用例2: 有进位的情况
        // l1: 9 -> 9 -> 9 -> 9
        // l2: 1
        ListNode l3 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        ListNode l4 = new ListNode(1);
        ListNode result2 = addTwoNumbers(l3, l4);
        printList(result2); // 应该输出: 0 -> 0 -> 0 -> 0 -> 1
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

    /**
     * LeetCode002,两数相加
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;

        // 同时遍历两个链表
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;  // 初始化为进位值

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;  // 立即移动指针
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;  // 立即移动指针
            }

            // 计算当前位和新的进位
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }

        return dummy.next;
    }


    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }


    /**
     * 定义链表节点
     */
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }


        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
