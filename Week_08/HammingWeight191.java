package com.xunlianying8.zuoye1;

// 第一遍
// 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
// 提示：
// 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
// 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
// 进阶：
// 如果调用这个函数多次，你将如何优化你的算法。
// 貌似对于最优解法，不存在是否调用多次的问题？？？
public class HammingWeight191 {
    /**
     * 位操作
     * 时间复杂度:O(1) - 98.84% - 运行时间与n中位为1的有关。在最坏情况下，n中所有位都是1。对于32位整数，运行时间是O(1)的。
     * 空间复杂度:O(1) - 96.46% - 没有使用额外空间
     * 优点: 位运算，代码简单易懂
     * 缺点:
     *
     * @param n
     * @return
     */
    public int hammingWeight1(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // 位元算：去掉最后一个1。
            count++;
        }
        return count;
    }

    /**
     * 循环和位移动
     * 时间复杂度:O(1) - 98.84% - 运行时间与n中位为1的有关。在最坏情况下，n中所有位都是1。对于32位整数，运行时间是O(1)的。
     * 空间复杂度:O(1) - 96.46% - 没有使用额外空间
     * 优点: 这个解法更容易被想到
     * 缺点:
     *
     * @param n
     * @return
     */
    public int hammingWeight2(int n) {
        int count = 0, temp = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & temp) != 0) count++;
            temp <<= 1;
        }
        return count;
    }
}
