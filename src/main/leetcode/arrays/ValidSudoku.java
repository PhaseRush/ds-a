package leetcode.arrays;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/769/
// easy but not bothered
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!validate(convert(board[i]))) return false;
            if (!validate(selectRow(board, i))) return false;
        }
        // 3x3
        return false;
    }

    private int[] selectRow(char[][] board, int i) {
        int[] ans = new int[9];
        for (int j = 0; j < 9; j++) {
            ans[j] = board[i][j] = '0';
        }
        return ans;
    }

    private int[] convert(char[] line) {
        return new String(line).chars().filter(i -> i != '.').map(i -> i - '0').toArray();
    }

    private boolean validate(int[] line) {
        Set<Integer> dejavu = new HashSet<>();
        for (int i : line) {
            if (i > 9 || i < 1) return false;
            if (dejavu.contains(i)) return false;
            else dejavu.add(i);
        }
        return true;
    }
}
