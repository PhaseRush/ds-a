package main.leetcode.arrays;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/674/
// 12 mins
public class IntersectionOfTwoArrays2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(
                new int[]{4, 9, 5},
                new int[]{9, 4, 9, 8, 4}
        )));


        int[] in1 = new int[]{64, 65, 2, 66, 4, 5, 69, 6, 7, 72, 73, 74, 74, 75, 78, 79, 80, 80, 21, 85, 22, 87, 24, 24, 88, 25, 89, 89, 26, 27, 27, 93, 93, 33, 98, 37, 38, 39, 45, 47, 49, 55, 56, 57, 58, 58};
        int[] in2 = new int[]{21, 47, 80, 4, 24, 87, 22, 6, 89, 80, 74, 74, 55, 58, 56, 98, 66, 49, 27, 78, 24, 69, 88, 80, 65, 72, 5, 64, 7, 37, 2, 75, 93, 79, 39, 85, 26, 93, 74, 89, 27, 57, 45, 73, 25, 33, 38, 58};
        Arrays.sort(in1);
        Arrays.sort(in2);

        int[] actual = intersect(
                new int[]{21, 47, 80, 4, 3, 24, 87, 12, 22, 11, 48, 6, 89, 80, 74, 71, 74, 55, 58, 56, 4, 98, 40, 66, 49, 53, 62, 27, 3, 66, 78, 24, 48, 69, 88, 12, 80, 63, 98, 65, 46, 35, 72, 5, 64, 72, 7, 29, 37, 3, 2, 75, 44, 93, 79, 78, 13, 39, 85, 26, 15, 41, 87, 47, 29, 93, 41, 74, 6, 48, 17, 89, 27, 61, 2, 68, 99, 57, 45, 73, 25, 33, 38, 32, 58},
                new int[]{1, 39, 6, 81, 85, 10, 38, 22, 0, 89, 57, 93, 58, 69, 65, 80, 84, 24, 27, 54, 37, 36, 26, 88, 2, 7, 24, 36, 51, 5, 74, 57, 45, 56, 55, 67, 25, 33, 78, 49, 16, 79, 74, 80, 36, 27, 89, 49, 64, 73, 96, 60, 92, 31, 98, 72, 22, 31, 0, 93, 70, 87, 80, 66, 75, 69, 81, 52, 94, 90, 51, 90, 74, 36, 58, 38, 50, 9, 64, 55, 4, 21, 49, 60, 65, 47, 67, 8, 38, 83}
        );
        Arrays.sort(actual);

        System.out.println("Action:\t\t" + Arrays.toString(actual));
        System.out.println("expected:\t" + Arrays.toString(in2));

        System.out.println(gcd(3, 1, 2, 2, 2, 3, 3, 3, 3));
    }

    public static int gcd(int... numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .orElseThrow()
                .getKey();
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Long> first = Arrays.stream(nums1).boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        Map<Integer, Long> second = Arrays.stream(nums2).boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        return first.entrySet().stream()
                .filter(e -> second.containsKey(e.getKey()))
                .flatMap(e -> Stream.generate(e::getKey).limit(e.getValue()))
                .mapToInt(Integer::intValue).toArray();
    }


}
