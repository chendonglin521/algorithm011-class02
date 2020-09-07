package com.xunlianying2;

// 第三遍 重点 - 动态规划（）的超级典型例子
// 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
// 思路：
// 刚看到题目的时候，只想到了2、3、5分别求幂，存成数组，但是如何递增条件判断，没想明白，看了动态规划的题解，发现自己就差了一步，取min值就可以了，吸取经验。
// 动态规划
public class NthUglyNumber49 {

    /**
     * 动态规划 - 想法很妙， 把
     * 时间复杂度：O(N) ： 其中N=n ，动态规划需遍历计算dp列表。 - 99.30%
     * 空间复杂度：O(N) ： 长度为N的dp 列表使用O(N)的额外空间。 - 93.74%
     *
     * @param n
     * @return
     */
    public int nthUglyNumber1(int n) {
        if (n == 0) return n;
        int[] result = new int[n];
        result[0] = 1;
        int count2 = 0, count3 = 0, count5 = 0;
        for (int i = 1; i < n; i++) {
            int n2 = result[count2] * 2, n3 = result[count3] * 3, n5 = result[count5] * 5;
            result[i] = Math.min(Math.min(n2, n3), n5);
            if (result[i] == n2) count2++; // 这里的判断不可以使用ifelse，因为两个判断语句都可能为真，而且都要执行。
            if (result[i] == n3) count3++; // 这里的判断不可以使用ifelse，因为两个判断语句都可能为真，而且都要执行。
            if (result[i] == n5) count5++; // 这里的判断不可以使用ifelse，因为两个判断语句都可能为真，而且都要执行。
        }
        return result[n - 1];
    }
}
