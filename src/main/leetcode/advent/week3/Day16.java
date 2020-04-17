package main.leetcode.advent.week3;

import java.util.ArrayDeque;
import java.util.Deque;

public class Day16 {
    public static void main(String[] args) {
        System.out.println(checkValidString("(*))********"));
    }

    public static boolean checkValidString(String s) {
        Deque<Integer> openIdx = new ArrayDeque<>(s.length());
        Deque<Integer> starIdx = new ArrayDeque<>(s.length());

        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case '(' -> openIdx.push(i);
                case '*' -> starIdx.push(i);
                default -> {
                    if (openIdx.isEmpty() && starIdx.isEmpty()) {
                        return false; // no open, return
                    } else if (!openIdx.isEmpty()) {
                        openIdx.pop(); // try to use open bracket first
                    } else {
                        starIdx.pop(); // or else have to use a star
                    }
                }
            }
        }

        while (!openIdx.isEmpty() && !starIdx.isEmpty()) {
            if (openIdx.pop() > starIdx.pop()) {
                return false;
            }
        }
        return openIdx.isEmpty();
    }
}
