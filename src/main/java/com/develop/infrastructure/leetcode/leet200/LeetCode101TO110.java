package com.develop.infrastructure.leetcode.leet200;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : jinxiaobo
 * @date : 2026年05月07日 22:50:29
 * @description :
 */
public class LeetCode101TO110 {

    public static void main(String[] args) {

        /**
         * LeetCode101 二叉树的深度
         *          3
         *      9        20
         *    2        15  7
         * 先从3开始一直遍历到2的位置，2的left和right都是0，这里出去2这一层就是1
         * 接着递归完成2之后回到9的right去做递归，发现没有这个时候返回0，接着就是Math.max（leftDepth, rightDepth）+1 = 1+1 =2 9 这一层返回了2
         * 接着递归9完成之后递归20
         * 20遍历左边15,15的left和right都为空，所以返回两个0,0+0+1 = 1
         * 20遍历右边7,7的left和right都为空，所以返回两个0,0+0+1 = 1
         * 接着回到20,20通过获取Math.max（leftDepth, rightDepth）+1 = 1 + 1 = 2
         * 最后出去的时候通过，因为两边的深度都是2,最后加上最外层的深度
         * Math.max（leftDepth, rightDepth）+1 = 2 + 1 = 3
         */
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        root1.left.left = new TreeNode(2);
        System.out.println(maxDepth(root1));
    }

    public static int maxDepth(TreeNode root) {
        // 递归终止条件：到达空节点，深度为0
        if (root == null) {
            return 0;
        }

        // 递归计算左右子树的最大深度
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // 当前树的深度 = 左右子树深度的最大值 + 1（当前节点）
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size(); // 当前层的节点数
            depth++; // 进入新的一层，深度+1
            // 遍历当前层的所有节点
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 将下一层的子节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return depth;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
