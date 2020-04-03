package main.leetcode.advent;

import java.util.Arrays;

public class Day1 {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1}));
    }
    public static int singleNumber(int[] nums) {
        return Arrays.stream(nums).reduce((a, b) -> a ^ b).orElseThrow();
    }
}
