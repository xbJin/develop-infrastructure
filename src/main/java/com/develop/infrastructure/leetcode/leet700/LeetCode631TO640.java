package com.develop.infrastructure.leetcode.leet700;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author : jinxiaobo
 * @date : 2026年05月07日 22:18:31
 * @description :
 */
public class LeetCode631TO640 {

    public static void main(String[] args) {

        /**
         * leetCode 637 二叉树的平均值
         */
        // 测试用例1：正常情况
        /**
         *          3               这一层的levelSize = 1
         *      9        20         这一层的levelSize = 2
         *    2        15  7        这一层的levelSize = 3
         */
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        root1.left.left = new TreeNode(2);
        System.out.println("测试用例1 - 正常二叉树:");
        System.out.println("BFS结果: " + averageOfLevels(root1));
        System.out.println("DFS结果: " + averageOfLevelsDFS(root1));

    }

    /**
     * 计算二叉树的平均值
     *
     * @return
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        // 边界条件检查
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();  // 使用ArrayDeque，性能更好
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // 当前层的节点数量
            double levelSum = 0.0;  // 直接使用double累加
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode != null){
                    levelSum += currentNode.val;
                    // 添加子节点
                    if (currentNode.left != null) {
                        queue.offer(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.offer(currentNode.right);
                    }
                }
            }

            // 计算平均值
            result.add(levelSum / levelSize);
        }

        return result;
    }

    /**
     * 使用DFS递归解法（对比）
     */
    public static List<Double> averageOfLevelsDFS(TreeNode root) {
        List<Double> result = new ArrayList<>();
        List<Long> sums = new ArrayList<>();  // 各层和
        List<Integer> counts = new ArrayList<>();  // 各层节点数

        dfs(root, 0, sums, counts);

        for (int i = 0; i < sums.size(); i++) {
            result.add((double) sums.get(i) / counts.get(i));
        }

        return result;
    }

    private static void dfs(TreeNode node, int level, List<Long> sums, List<Integer> counts) {
        if (node == null) return;

        if (level == sums.size()) {
            sums.add((long) node.val);
            counts.add(1);
        } else {
            sums.set(level, sums.get(level) + node.val);
            counts.set(level, counts.get(level) + 1);
        }

        dfs(node.left, level + 1, sums, counts);
        dfs(node.right, level + 1, sums, counts);
    }


    /**
     * 定义树节点,二叉树
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

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
