package main.leetcode.advent.week1;

import java.util.HashSet;
import java.util.Set;

public class Day2 {
    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }


    private static int f(int i) {
        int res = 0;
        int copy = i;
        int ones;
        while (copy != 0) {
            ones = copy % 10;
            res += ones * ones;
            copy /= 10;
        }
        return res;
    }

    public static boolean isHappy(int n) {
        Set<Integer> dejaVu = new HashSet<>();
        while (n != 1) {
            if (dejaVu.contains(n)) return false;
            dejaVu.add(n);
            n = f(n);
        }
        return true;
    }
}
