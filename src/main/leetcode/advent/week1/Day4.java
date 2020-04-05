package main.leetcode.advent.week1;

import java.util.Arrays;

public class Day4 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 0, 4, 12, 123, 0};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        for (; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
        }
    }
}
