package com.develop.infrastructure.leetcode.leet100;

/**
 * @author : jinxiaobo
 * @date : 2026年04月19日 13:55:43
 * @description :
 */
public class LeetCode021T0030 {

    public static void main(String[] args) {
        /**
         * 合并了两个有序链表：21题
         */
        testMergetList();
    }

    public static void testMergetList(){
        // 测试用例1
        ListNode l1 = createList(new int[]{1, 2, 4});
        ListNode l2 = createList(new int[]{1, 3, 4});
        ListNode result1 = mergeTwoLists(l1, l2);
        printList("测试用例1: ", result1);

        // 测试用例2
        ListNode l3 = createList(new int[]{});
        ListNode l4 = createList(new int[]{});
        ListNode result2 = mergeTwoLists(l3, l4);
        printList("测试用例2: ", result2);

        // 测试用例3
        ListNode l5 = createList(new int[]{});
        ListNode l6 = createList(new int[]{0});
        ListNode result3 = mergeTwoLists(l5, l6);
        printList("测试用例3: ", result3);

        // 测试用例4：复杂情况
        ListNode l7 = createList(new int[]{2, 5, 8, 9});
        ListNode l8 = createList(new int[]{1, 3, 4, 7, 10});
        ListNode result4 = mergeTwoLists(l7, l8);
        printList("测试用例4: ", result4);

        // 测试递归解法
        ListNode l9 = createList(new int[]{1, 2, 4});
        ListNode l10 = createList(new int[]{1, 3, 4});
        ListNode result5 = mergeTwoListsRecursive(l9, l10);
        printList("递归解法: ", result5);
    }


    // 创建链表
    private static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        for (int num : arr) {
            // 先赋值,这样current.next就是一个新的链表
            current.next = new ListNode(num);
            // 再重置，那么其实current = new ListNode(num);,下一次处理的其实就是new ListNode
            current = current.next;
        }

        return dummy.next;
    }

    // 打印链表
    private static void printList(String message, ListNode head) {
        System.out.print(message);
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println(" -> null");
    }


    /**
     * 合并两个有序链表
     * 方法1：迭代法（推荐）
     * 时间复杂度：O(n+m)，空间复杂度：O(1)
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 创建虚拟头节点，简化边界条件处理
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 同时遍历两个链表
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                // 拿到赋值
                current.next = l1;
                l1 = l1.next;
            } else {
                // 先赋值,这样current.next就是l2
                current.next = l2;
                l2 = l2.next;
            }
            // 再重置，current.next继续处理给下一个节点赋值
            current = current.next;
        }

        // 处理剩余部分
        // 如果l1还有剩余节点，直接连接到current后面
        // 如果l2还有剩余节点，也直接连接到current后面
        current.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }

    /**
     * 合并两个有序链表
     * 方法2：递归法
     * 时间复杂度：O(n+m)，空间复杂度：O(n+m)（递归栈深度）
     */
    public static ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        // 递归终止条件
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    /**
     * 定义链表
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
