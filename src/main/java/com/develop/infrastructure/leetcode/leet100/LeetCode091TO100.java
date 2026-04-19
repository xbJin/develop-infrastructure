package com.develop.infrastructure.leetcode.leet100;

import io.micrometer.common.util.StringUtils;

/**
 * @author : jinxiaobo
 * @date : 2026年04月13日 23:24:57
 * @description :
 */
public class LeetCode096TO100 {

    public static void main(String[] args) {
        System.out.println(numTrees1(3));
        System.out.println(numTrees2(3));

        System.out.println(isInterleave01("aabcc", "dbbca", "aadbbcbcac"));
    }


    /**
     * 解码方式
     * @return
     */
    public static int getDecodeMethod(String code){
        // 空字符串或者0开头的
        if (StringUtils.isBlank(code) || code.startsWith("0")){
            return 0;
        }
        if (code.length() == 1){
            return 1;
        }
        /**
         * 这里应该是1-26对应字母
         */
        char[] charArray = code.toCharArray();


        return 0;
    }





    /**
     * 不同的二叉搜索树: 卡特兰数,动态规划
     * @param n
     * @return
     */
    public static int numTrees1(int n){
        int[] dp = new int[n+1];
        // 空树
        dp[0] = 1;
        // 1个节点只有一种情况
        dp[1] = 1;
        // 计算2 ~ n的情况
        for (int i = 2; i <= n; i++) {
            // 以j为根节点,左子树有j-1个节点，右子树有i-j个节点
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }


    public static int numTrees2(int n) {
        // 直接用卡特兰数公式计算
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * (4L * i - 2) / (i + 1);
        }
        return (int) result;
    }


    /**
     * 方法一：一维DP（空间优化）
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave01(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        int m = s1.length(), n = s2.length();
        // 使用一维数组，dp[j] 表示当前行第j列的状态
        boolean[] dp = new boolean[n + 1];

        // 初始化第一行
        dp[0] = true;
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }

        // 逐行更新
        for (int i = 1; i <= m; i++) {
            // 更新当前行的第一个元素
            dp[0] = dp[0] && s1.charAt(i-1) == s3.charAt(i-1);

            for (int j = 1; j <= n; j++) {
                // dp[j] 在更新前代表上一行的dp[j]（即dp[i-1][j]）
                // dp[j-1] 已经更新，代表当前行的dp[j-1]（即dp[i][j-1]）
                dp[j] = (dp[j] && s1.charAt(i-1) == s3.charAt(i+j-1)) ||
                        (dp[j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }

        return dp[n];
    }

    /**
     * 方法二：二维DP,便于理解
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave02(String s1, String s2, String s3) {
        // 长度检查
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        int m = s1.length(), n = s2.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 初始化
        dp[0][0] = true;

        // 初始化第一行：只使用s2
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }

        // 初始化第一列：只使用s1
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        }

        // 填充DP表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) ||
                        (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }

        return dp[m][n];
    }

}
