package leetcode.arrays;

import java.util.Arrays;

// https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/559/
public class PlusOne {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                plusOne(new int[]{9, 9})
        ));
    }

    public static int[] plusOne(int[] digits) {

        boolean acc = true;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + (acc ? 1 : 0);
            if (sum > 9) acc = true;
            else acc = false;
            digits[i] = sum % 10;
        }

        if (acc) {
            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            System.arraycopy(digits, 0, ans, 1, digits.length);
            return ans;
        } else {
            return digits;
        }
    }
}
