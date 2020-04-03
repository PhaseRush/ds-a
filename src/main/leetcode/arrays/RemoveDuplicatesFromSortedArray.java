package main.leetcode.arrays;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int[] unique = set.stream().mapToInt(i -> i).toArray();
        Arrays.sort(unique);
        System.arraycopy(unique, 0, nums, 0, unique.length);
        return unique.length;
    }
}
