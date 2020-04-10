package main.leetcode.advent.week2;

import java.util.Stack;

public class Day9 {
    public static void main(String[] args) {
        System.out.println(backspaceCompare("a##c", "#a#c"));
    }

    private static Stack<Character> f(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .collect(Stack::new, (st, ch) -> {
                    if (ch == '#') {
                        if (!st.isEmpty()) st.pop();
                    } else st.push(ch);
                }, (a, b) -> {
                });
    }

    public static boolean backspaceCompare(String S, String T) {
        return f(S).equals(f(T));
    }
}
