package com.xunlianying6;

// 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
// 若这两个字符串没有公共子序列，则返回 0。
// 思路：
//
public class LongestCommonSubsequence1143 {

    /***
     * 递归（自顶向下）- 超时
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) return 0;
        // return helper1(text1.toCharArray(), text2.toCharArray(), 0, 0);
        char[] array1 = text1.toCharArray();
        char[] array2 = text2.toCharArray();
        return helper2(array1, array2, 0, 0, new int[array1.length][array2.length]);
    }

    //超时：纯粹递归
    public int helper1(char[] array1, char[] array2, int index1, int index2) {
        if (index1 == array1.length || index2 == array2.length) return 0;
        if (array1[index1] == array2[index2]) {
            return 1 + helper1(array1, array2, index1 + 1, index2 + 1);
        } else {
            return Math.max(helper1(array1, array2, index1 + 1, index2), helper1(array1, array2, index1, index2 + 1));
        }
    }

    // 超时：加了二维缓存数组
    public int helper2(char[] array1, char[] array2, int index1, int index2, int[][] result) {
        // terminator
        if (index1 == array1.length || index2 == array2.length) return 0;
        // process
        if (result[index1][index2] == 0) {
            if (array1[index1] == array2[index2]) {
                return 1 + helper2(array1, array2, index1 + 1, index2 + 1, result);
            } else {
                return Math.max(helper2(array1, array2, index1 + 1, index2, result), helper2(array1, array2, index1, index2 + 1, result));
            }
        }
        return result[index1][index2];
    }

    /**
     * 动态规划（自底向上）
     * 时间复杂度:O(m*n) - 74.78%
     * 空间复杂度:O(m*n) - %
     * 优点:
     * 缺点:
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence11(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        // 这里用第0行和第0列解决了所有初始逻辑的问题。经验尚浅，这个真的太棒了，上次自己也是这么做的，不过只是碰巧用到，这次自己碰巧没用到
        // 但是这个真的是太妙了
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                // 我一开始的思路是转化成char数组，其实直接用charat更简单明了
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }

    /**
     * 动态规划（自底向上） + 状态压缩(一维数组)
     * 时间复杂度:O(m*n) - 77.97%
     * 空间复杂度:O(n) - %
     * 优点:
     * 缺点: string的charat如果改用char数组会更好用
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence111(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        int len1 = text1.length(), len2 = text2.length();
        if (len1 == 0 || len2 == 0) return 0;
        int[] dp = new int[len2 + 1];
        for (int i = 1; i <= len1; i++) {
            int temp = 0;
            for (int j = 1; j <= len2; j++) {
                int prev = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = temp + 1;
                } else {
                    dp[j] = Math.max(prev, dp[j - 1]);
                }
                temp = prev;
            }
        }
        return dp[len2];
    }

    /***
     * 动态规划（自底向上）+ 状态压缩（一维数组）
     * 时间复杂度:O(m*n) - 99.98%
     * 空间复杂度:O(max(n,m)) - 93.67%
     * 优点: 跟上一解法的区别在于，把字符串转换成数组，而不是使用string的charat，
     * 缺点: 其实使用大的字符串做为for循环的外侧，完全没必要。
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence1111(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        int len1 = text1.length(), len2 = text2.length();
        if (len1 == 0 || len2 == 0) return 0;
        char[] colText, rowText;
        //
        if (len1 > len2) {
            colText = text2.toCharArray();
            rowText = text1.toCharArray();
        } else {
            colText = text1.toCharArray();
            rowText = text2.toCharArray();
        }
        int[] dp = new int[colText.length + 1];
        for (int i = 1; i <= rowText.length; i++) {
            int tmp = 0;
            for (int j = 1; j <= colText.length; j++) {
                int prev = tmp;
                tmp = dp[j];
                if (rowText[i - 1] == colText[j - 1]) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colText.length];
    }
}
