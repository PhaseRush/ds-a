package main.leetcode.misc;

import java.util.stream.IntStream;

public class CheckStraightLine {

    public static void main(String[] args) {
        System.out.println(checkStraightLine(new int[][]
//                {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}
//                        {{0, 0}, {0, 5}, {5, 5}, {5, 0}}
                        {{-3, -2}, {-1, -2}, {2, -2}, {-2, -2}, {0, -2}}
        ));
    }

    public static boolean checkStraightLine(int[][] coordinates) {
        return IntStream.range(0, coordinates.length - 1)
                .mapToDouble(idx -> {
                    final int rise = coordinates[idx + 1][1] - coordinates[idx][1];
                    final int run = coordinates[idx + 1][0] - coordinates[idx][0];
                    System.out.println(rise + "\t" + run);
                    return run == 0 ? Integer.MIN_VALUE : ((double) rise) / run + 0.0d;
                })
//                .peek(System.out::println)
                .distinct()
                .count() <= 1;

    }
}
