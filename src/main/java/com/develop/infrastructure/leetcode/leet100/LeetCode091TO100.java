package com.develop.infrastructure.leetcode.leet100;

import io.micrometer.common.util.StringUtils;

/**
 * @author : jinxiaobo
 * @date : 2026年04月13日 23:24:57
 * @description :
 */
public class LeetCode091TO100 {

    public static void main(String[] args) {
        System.out.println(numTrees1(3));
        System.out.println(numTrees2(3));

        System.out.println(isInterleave01("aabcc", "dbbca", "aadbbcbcac"));
    }


    /**
     * LeetCode91 解码方法
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        // 字符串长度
        int n = s.length();

        // --------------------- 边界情况 ---------------------
        // 如果字符串为空，或者第一个字符是 0，直接返回 0（无法解码）
        if (n == 0 || s.charAt(0) == '0') {
            return 0;
        }

        // --------------------- 定义DP数组 ---------------------
        // dp[i] = 前 i 个字符的解码方法总数
        int[] dp = new int[n + 1];

        // --------------------- 初始化 ---------------------
        dp[0] = 1;          // 空字符串，解码方法为1（基准）
        dp[1] = 1;          // 第一个字符非0，所以有一种方法

        // --------------------- 开始递推 ---------------------
        for (int i = 2; i <= n; i++) {
            // --------------------- 情况1：单独解码第 i 个字符 ---------------------
            // 第 i 个字符对应 s 的下标是 i-1
            char oneChar = s.charAt(i - 1);
            if (oneChar != '0') {
                // 单独解码合法，方法数 = 前 i-1 个字符的方法数
                dp[i] += dp[i - 1];
            }

            // --------------------- 情况2：和前一个字符组合解码 ---------------------
            // 取两位字符：i-2 和 i-1 位置
            String twoStr = s.substring(i - 2, i);
            int twoNum = Integer.parseInt(twoStr);
            // 必须满足 10~26 才合法
            if (twoNum >= 10 && twoNum <= 26) {
                // 组合解码合法，方法数 += 前 i-2 个字符的方法数
                dp[i] += dp[i - 2];
            }
        }

        // --------------------- 返回最终结果 ---------------------
        return dp[n];
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
