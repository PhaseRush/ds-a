package main.leetcode.advent;

public class Day3 {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public static int maxSubArray(int[] nums) {
        int prevMax = nums[0], globalMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prevMax = Math.max(nums[i], nums[i] + prevMax);
            globalMax = Math.max(prevMax, globalMax);
        }
        return globalMax;
    }
}
