package main.leetcode.advent.week2;

public class Day14 {

    public static void main(String[] args) {
//        int[][] shifts = new int[][]{{0, 1}, {1, 2}};
//        int[][] shifts = new int[][]{{0, 1}};
        int[][] shifts = new int[][]{
                {1, 4}, {0, 5}, {0, 4}, {1, 1}, {1, 5}
        };
        System.out.println(stringShift("mecsk", shifts));
    }

    public static String stringShift(String s, int[][] shift) {
        int totalRightShift = 0;
        for (int[] ints : shift) {
            totalRightShift += ints[0] == 0 ? -ints[1] : ints[1];
        }
        totalRightShift %= s.length();
        System.out.println(totalRightShift);
        if (totalRightShift == 0) return s;
        else if (totalRightShift > 0) {
            int divide = s.length() - totalRightShift;
            return s.substring(divide) + s.substring(0, divide);
        } else {
            totalRightShift *= -1;
            return s.substring(totalRightShift) + s.substring(0, totalRightShift);
        }
    }
}
