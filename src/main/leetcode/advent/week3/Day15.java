package main.leetcode.advent.week3;

import java.util.Arrays;

public class Day15 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                productExceptSelf(new int[]{
                        1, 2, 3, 4, 0
                })
        ));
    }

    public static int[] productExceptSelf(int[] nums) {
        final int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = 1;
        right[len - 1] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i - 1];
            right[len - i - 1] = right[len - i] * nums[len - i];
        }
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }
}
