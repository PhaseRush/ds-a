package leetcode.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

// https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/578/
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        return Arrays.stream(nums).boxed().collect(Collectors.toSet()).size() != nums.length;
    }
}
