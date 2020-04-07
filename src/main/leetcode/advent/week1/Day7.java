package main.leetcode.advent.week1;

import kotlin.Pair;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/*
Given an integer array arr, count element x such that x + 1 is also in arr.

If there're duplicates in arr, count them seperately.



Example 1:

Input: arr = [1,2,3]
Output: 2
Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
Example 2:

Input: arr = [1,1,3,3,5,5,7,7]
Output: 0
Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.
Example 3:

Input: arr = [1,3,2,3,5,0]
Output: 3
Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
Example 4:

Input: arr = [1,1,2,2]
Output: 2
Explanation: Two 1s are counted cause 2 is in arr.


Constraints:

1 <= arr.length <= 1000
0 <= arr[i] <= 1000
 */
public class Day7 {
    public static void main(String[] args) {
        int[] in = {1, 2, 3};
        System.out.println(otherOneLiner(in));
    }

    public static int oneLiner(int[] arr) {
        return Arrays.stream(arr).boxed()
                .map(i -> new Pair<>(i, Arrays.stream(arr).boxed().collect(Collectors.toSet())))
                .map(pair -> pair.getSecond().contains(pair.getFirst() + 1) ? 1 : 0)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    public static int otherOneLiner(int[] arr) {
        return Arrays.stream(arr).boxed()
                .map(i -> Arrays.stream(arr).boxed().collect(Collectors.toSet()).contains(i + 1) ? 1 : 0)
                .reduce(Integer::sum)
                .orElseThrow();
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
