package com.xunlianying1;

import java.util.Stack;

// 第四遍 - 重点：解法1, 解法2太麻烦一点儿都不想看哈哈哈
// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
public class trap42 {
    /**
     * 动态规划
     * 时间复杂度:O(n) - 99.99%
     * 空间复杂度:O() - 99.35%
     * 优点:
     * 缺点:
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int result = 0, len = height.length, min;
        int[] left = new int[len], right = new int[len];
        for (int i = 1; i < len - 1; i++) { //1 ~ len-2 的max
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }
        for (int i = len - 2; i > 0; i--) { //1 ~ len-2的max
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < len - 1; i++) { //1 ~ len-2
            min = Math.min(left[i], right[i]);
            if (min > height[i]) result += (min - height[i]);
        }
        return result;
    }

    /**
     * 双指针
     * 时间复杂度:O(n) - 99.99%
     * 空间复杂度:O(1) - 37.35%
     * 优点:
     * 缺点:
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int result = 0, len = height.length;
        if (len < 1) return result;
        int left = 0, right = len - 1, left_height = 0, right_height = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] < left_height) result += left_height - height[left];
                else left_height = height[left];
                left++;
            } else {
                if (height[right] < right_height) result += right_height - height[right];
                else right_height = height[right];
                right--;
            }
        }
        return result;
    }

    /**
     * 栈
     * 时间复杂度:O(n) - 38.01%
     * 空间复杂度:O() -
     * 优点:
     * 缺点:
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int sum = 0, current = 0, h, distance, min;
        Stack<Integer> stack = new Stack<>();
        while (current < height.length) {
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) break;
                distance = current - stack.peek() - 1; //两堵墙之前的距离。
                min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }

    /**
     * 按列求
     * 时间复杂度:O() - 11.02%
     * 空间复杂度:O() - 69.82%
     * 优点:
     * 缺点:
     *
     * @param height
     * @return
     */
    public int trap4(int[] height) {
        int sum = 0, max_left, max_right, min;
        for (int i = 1; i < height.length - 1; i++) {
            max_left = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) max_left = height[j];
            }
            max_right = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) max_right = height[j];
            }
            min = Math.min(max_left, max_right);
            if (min > height[i]) sum = sum + (min - height[i]);
        }
        return sum;
    }
}
