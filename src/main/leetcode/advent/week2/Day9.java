package main.leetcode.advent.week2;

import java.util.Stack;

public class Day9 {
    public static void main(String[] args) {
        System.out.println(backspaceCompare("a##c", "#a#c"));
    }

    public static boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack<>();
        for (char c : S.toCharArray()) {
            if (c == '#') {
                if (!stackS.isEmpty()) stackS.pop();
            } else {
                stackS.push(c);
            }
        }
        Stack<Character> stackT = new Stack<>();
        for (char c : T.toCharArray()) {
            if (c == '#') {
                if (!stackT.isEmpty()) stackT.pop();
            } else {
                stackT.push(c);
            }
        }
        return stackS.equals(stackT);
    }
}
