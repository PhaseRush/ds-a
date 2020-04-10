package main.leetcode.advent.week2;

import java.util.Stack;

public class Day9 {
    public static void main(String[] args) {
        System.out.println(backspaceCompare("a##c", "#a#c"));
    }

    private static Stack<Character> f(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack;
    }

    public static boolean backspaceCompare(String S, String T) {
        return f(S).equals(f(T));
    }
}
