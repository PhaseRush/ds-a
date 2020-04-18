package main.leetcode.advent.week3;

import java.util.Arrays;

public class Day18 {

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{
//                {1, 2},
//                {1, 1}
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
    }

    public static int minPathSum(int[][] grid) {
        final int n = grid.length;
        if (n == 0) return 0;
        final int m = grid[0].length;
        final int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            dp[i] = Arrays.copyOf(grid[i], m);
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i][0] + dp[i - 1][0];
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + dp[i][j];
//                System.out.println(i + "\t" + j + "\t" + dp[i][j]);
            }
        }
        return dp[n - 1][m - 1];
    }
}
