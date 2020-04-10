package main.leetcode.advent.week2;

import java.util.Stack;

public class Day10 {
    public static void main(String[] args) {
        MinStack s = new MinStack();
        s.push(-2);
        s.push(-0);
        s.push(-3);
        System.out.println();
    }


    static class MinStack {
        Stack<Integer> stack;
        Stack<Integer> mins;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<>();
            mins = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (mins.isEmpty()) {
                mins.push(x);
            } else {
                mins.push(Math.min(mins.peek(), x));
            }
        }

        public void pop() {
            stack.pop();
            mins.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return mins.peek();
        }
    }
}
