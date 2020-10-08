package com.xunlianying1;

import java.util.*;

// 第二遍
// 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
// 有效字符串需满足：
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
// 注意空字符串可被认为是有效字符串。
public class isValid20 {
    /**
     * 数组模拟栈 + ifelse
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 98.38%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public boolean isValid1(String s) { // 不用判断s为空或者长度为奇数的情况，影响速度，下面已经把这些情况包含了。
        char[] charArray = new char[s.length() + 1];
        int p = 1; // 因为存在第一个符号就是右～，所以p==0的情况是用来考虑越界的。
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                charArray[p++] = c;
            } else {
                p--;
                if (c == ')' && charArray[p] != '(') return false;
                if (c == '}' && charArray[p] != '{') return false;
                if (c == ']' && charArray[p] != '[') return false;
            }
        }
        return p == 1; // 如果左括号还有剩余 括号没有一一对应，属于无效情况
    }

    /**
     * 数组模拟栈 + case
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 75.09%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        int length = s.length();
        if (length == 0) return true;
        if ((length & 1) == 1) return false; // 位运算更高效
        int index = 0;
        char[] stack = new char[length];
        for (int i = 0; i < length; i++) {
            switch (s.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    stack[index++] = s.charAt(i);
                    continue;
                case ')':
                    if (index == 0 || stack[--index] != '(') return false;
                    continue;
                case ']':
                    if (index == 0 || stack[--index] != '[') return false;
                    continue;
                case '}':
                    if (index == 0 || stack[--index] != '{') return false;
                    continue;
            }
        }
        return index == 0;
    }

    /**
     * 栈： Stack
     * 时间复杂度:O() - 78.03%
     * 空间复杂度:O() - 85.25%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public boolean isValid3(String s) {
        int length = s.length();
        if (length % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char theChar = s.charAt(i);
            if (theChar == '(' || theChar == '{' || theChar == '[')
                stack.push(theChar);
            else {
                if (stack.empty()) // 栈中已无左括号，此时字符为右括号，无法匹配。
                    return false;
                char preChar = stack.peek();
                if ((preChar == '{' && theChar == '}') || (preChar == '(' && theChar == ')') || (preChar == '[' && theChar == ']'))
                    stack.pop();
                else return false;
            }
        }
        return stack.empty();
    }

    /**
     * 栈 - Deque(LinkedList)
     * 时间复杂度:O(n) - 78.08%
     * 空间复杂度:O(n) - 85.73%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public boolean isValid4(String s) {
        int len = s.length();
        if (len % 2 == 1) return false;
        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> deque = new LinkedList<Character>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                if (deque.peek() != map.get(ch) || deque.isEmpty()) return false;
                deque.pop();
            } else {
                deque.push(ch);
            }
        }
        return deque.isEmpty();
    }

    /**
     * 栈 - LinkedList
     * 时间复杂度:O() - 78.08%
     * 空间复杂度:O() - 13.74%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public boolean isValid5(String s) {
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{
            add('?');
        }};
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) stack.addLast(c);
            else if (map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }

    private static final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('{', '}');
        put('[', ']');
        put('(', ')');
        put('?', '?');
    }};

    /**
     * 字符串contains
     * 时间复杂度:O() - 5.00%
     * 空间复杂度:O() - 5.02%
     * 优点:
     * 缺点:
     *
     * @param s
     * @return
     */
    public boolean isValid6(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            if (s.contains("{}")) s = s.replace("{}", "");
            if (s.contains("()")) s = s.replace("()", "");
            if (s.contains("[]")) s = s.replace("[]", "");
        }
        return s.isEmpty();
    }
}
