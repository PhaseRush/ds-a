package main.leetcode.arrays;

import java.util.*;

// https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/769/
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> dejavu = new HashMap<>();

        for (int i1 = 0; i1 < nums.length; i1++) {
            int i = nums[i1];
            if (dejavu.containsKey(target - i)) {
                return new int[]{i1, dejavu.get(target - i)};
            } else {
                dejavu.put(i, i1);
            }
        }
        return null;
    }
}
