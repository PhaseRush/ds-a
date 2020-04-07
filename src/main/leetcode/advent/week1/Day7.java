package main.leetcode.advent.week1;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) {
        int[] in = {1,2,3};
        System.out.println(countElements(in));
    }

    public static int countElements(int[] arr) {
        int res = 0;
        Set<Integer> dejaVu = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        for (int value : arr) {
            if (dejaVu.contains(value + 1)) {
                res++;
            }
        }
        return res;
    }
}
