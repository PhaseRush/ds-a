package main.leetcode.arrays;
// https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/567/
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
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
