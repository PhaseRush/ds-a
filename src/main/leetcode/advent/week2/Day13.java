package main.leetcode.advent.week2;

import java.util.HashMap;
import java.util.Map;

public class Day13 {
    public static void main(String[] args) {
        // System.out.println(findMaxLength(new int[]{0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1}));
        System.out.println(findMaxLength(new int[]{0, 1}));
    }

    public static int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>(); // map sum -> first index of occurrence
        map.put(0, -1); // handle case where entire array is valid
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : +1;
            if (map.containsKey(sum)) {
                max = Math.max(max, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return max;
    }

    // old solution, TLE

    public static int findMaxLength2(int[] nums) {
        return helper(nums, 0, nums.length);
    }

    private static boolean valid(int[] nums, int start, int end) {
        int zeros = 0, ones = 0;
        for (int i = start; i < end; i++) {
            if (nums[i] == 0) zeros++;
            else ones++;
        }
        return zeros == ones;
    }

    private static int helper(int[] nums, int start, int end) {
        if (valid(nums, start, end)) {
            return end - start;
        } else {
            return Math.max(
                    helper(nums, start + 1, end),
                    helper(nums, start, end - 1)
            );
        }
    }
}