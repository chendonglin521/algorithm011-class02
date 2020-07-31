package com.xunlianying6.zuoye1;

import java.util.*;

// 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
// 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
// 你需要计算完成所有任务所需要的最短时间。
// 思路：
// 最字问题，目前最适合想到动态规划
public class LeastInterval621 {

    /***
     * 递归 + BFS
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval1(char[] tasks, int n) {
        int level = 0, result = 0;
        bfs(tasks, n, level, result);
        return 0;
    }

    public void bfs(char[] tasks, int n, int level, int result) {

    }

    /**
     * 动态规划 - 三种实现方式，但是都是使用数组，再sort，所以本质没有差别
     * 时间复杂度:O() - 99.74%
     * 空间复杂度:O() - 33.87%
     * 优点:
     * 缺点:因为数据之前的值都会被重复使用，所以无法通过状态压缩来进行优化
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval11(char[] tasks, int n) {
        if (tasks.length <= 1 || n < 1) return tasks.length;
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            counts[tasks[i] - 'A']++;
        }
        Arrays.sort(counts);
        int maxCount = counts[25];
        int retCount = (maxCount - 1) * (n + 1) + 1;
        int i = 24;
        while (i >= 0 && counts[i--] == maxCount) {
            retCount++;
        }
        return Math.max(retCount, tasks.length);
    }

    public int leastInterval111(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle_slots -= Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }

    public int leastInterval1111(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0) break;
                if (i < 26 && map[25 - i] > 0) map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }

    /***
     * 优先队列
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
     * 优点:
     * 缺点:
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval111111(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks)
            map[c - 'A']++;
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        for (int f : map) {
            if (f > 0)
                queue.add(f);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List<Integer> temp = new ArrayList<>();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1)
                        temp.add(queue.poll() - 1);
                    else
                        queue.poll();
                }
                time++;
                if (queue.isEmpty() && temp.size() == 0)
                    break;
                i++;
            }
            for (int l : temp)
                queue.add(l);
        }
        return time;
    }
}
