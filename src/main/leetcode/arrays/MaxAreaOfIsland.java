package main.leetcode.arrays;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/max-area-of-island/
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        System.out.println(maxAreaOfIsland(new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        }));
    }

    private static final int[] DX = {1, 0, -1, 0};
    private static final int[] DY = {0, 1, 0, -1};

    private static int search(int[][] grid, int x, int y, boolean[][] visited) {
        Deque<Integer> xs = new ArrayDeque<>();
        Deque<Integer> ys = new ArrayDeque<>();
        xs.push(x);
        ys.push(y);
        int area = 0;
        while (!xs.isEmpty()) {
            int currX = xs.pop();
            int currY = ys.pop();
            if (currX >= 0 && currX < grid.length && currY >= 0 && currY < grid[0].length && grid[currX][currY] == 1 && !visited[currX][currY]) {
                visited[currX][currY] = true;
                area++;
                for (int i = 0; i < DX.length; i++) {
                    xs.push(currX + DX[i]);
                    ys.push(currY + DY[i]);
                }
            }
        }
        return area;
    }

    public static int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    maxArea = Math.max(search(grid, i, j, visited), maxArea);
                }
            }
        }
        return maxArea;
    }
}
