package leetcode.arrays;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/549/
public class SingleNumber {
    public int singleNumber(int[] nums) {
        Set<Integer> ints = new HashSet<>();
        for (int i : nums) {
            if (ints.contains(i)) ints.remove(i);
            else ints.add(i);
        }

        return (int) ints.toArray()[0];
    }
}
