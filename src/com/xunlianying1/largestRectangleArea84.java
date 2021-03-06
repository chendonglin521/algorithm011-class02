package com.xunlianying1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

// 第五遍 - 重要 解法1比较基础便于理解方法的精华， 解法2有空再去看，基本思想都是：原始数组前后加一位，其实就是双指针。
// 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
public class largestRectangleArea84 {
    /**
     * 单调栈（双指针）
     * 时间复杂度:O() - 99.77%
     * 空间复杂度:O() - 44.39%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length, result = 0, pos;
        if (len == 0) return 0;
        int[] left = new int[len], right = new int[len];
        left[0] = -1;
        right[len - 1] = len;
        for (int i = 1; i < len; i++) {
            pos = i - 1; // 左边的，也就是从heights的0开始
            while (pos >= 0 && heights[pos] >= heights[i]) pos = left[pos]; // 取heights[i]左边连续大于自身的最远出的left值，否则就取i-1
            left[i] = pos;
        }
        for (int i = len - 2; i >= 0; i--) {// 生成right数组：右侧最大值
            pos = i + 1; // 右边的，也就是从heights的len-1开始
            while (pos < len && heights[pos] >= heights[i]) pos = right[pos]; // 取heights[i]右边边连续大于自身的最远出的right值，否则就取i+1
            right[i] = pos;
        }
        for (int i = 0; i < len; i++) result = Math.max(result, (right[i] - left[i] - 1) * heights[i]);
        return result;
    }

    /**
     * Deque(ArrayDeque)
     * 时间复杂度:O() - 94.70%
     * 空间复杂度:O() - 81.54%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        int result = 0, len = heights.length, cur;
        Deque<Integer> deque = new ArrayDeque<>(); // 也可以用stack，但是ArrayDeque效率更高，不知道为什么，暂时搁置。
        int[] new_heights = new int[len + 2];
        for (int i = 1; i <= len; i++) {
            new_heights[i] = heights[i - 1];
        }
        for (int i = 0; i < len + 2; i++) {
            while (!deque.isEmpty() && new_heights[deque.peek()] > new_heights[i]) {
                cur = deque.pop();
                result = Math.max(result, (i - deque.peek() - 1) * new_heights[cur]);
            }
            deque.push(i);
        }
        return result;
    }

    /**
     * Deque(ArrayDeque)
     * 时间复杂度:O() - 90.58%
     * 空间复杂度:O() - 81.89%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        int len = heights.length, res = 0;
        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        len += 2;
        Deque<Integer> deque = new ArrayDeque<>(len);
        deque.addLast(0);
        int curHeight = 0, curWidth = 0;
        for (int i = 1; i < len; i++) {
            while (newHeights[i] < newHeights[deque.peekLast()]) {
                curHeight = newHeights[deque.pollLast()];
                curWidth = i - deque.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            deque.addLast(i);
        }
        return res;
    }

    /**
     * Stack
     * 时间复杂度:O() - 83.94%
     * 空间复杂度:O() - 59.50%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea4(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < len; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    /**
     * 栈
     * 时间复杂度:O(n) - 67.32%
     * 空间复杂度:O(n) - 51.74%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea5(int[] heights) {
        int len = heights.length;
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                    stack.pollLast();
                }
                int curWidth;
                if (stack.isEmpty()) {
                    curWidth = i;
                } else {
                    curWidth = i - stack.peekLast() - 1;
                }
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pollLast()];
            while (!stack.isEmpty() && heights[stack.peekLast()] == curHeight) {
                stack.pollLast();
            }
            int curWidth;
            if (stack.isEmpty()) {
                curWidth = len;
            } else {
                curWidth = len - stack.peekLast() - 1;
            }
            res = Math.max(res, curHeight * curWidth);
        }
        return res;
    }

    /**
     * 暴力解法
     * 时间复杂度:O() - 18.22%
     * 空间复杂度:O() - 16.83%
     * 优点:
     * 缺点:
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea6(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        int res = 0, left, right, curHeight;
        for (int i = 0; i < len; i++) {
            left = i;
            curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }
            right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }
            res = Math.max(res, (right - left + 1) * curHeight);
        }
        return res;
    }
}
