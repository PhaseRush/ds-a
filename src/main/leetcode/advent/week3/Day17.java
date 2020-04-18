package main.leetcode.advent.week3;

import java.util.ArrayDeque;
import java.util.Deque;

public class Day17 {
    public static void main(String[] args) {
        System.out.println(numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        }));
    }

    private static final int[] DX = {1, 0, -1, 0};
    private static final int[] DY = {0, 1, 0, -1};

    private static void search(char[][] grid, int x, int y, boolean[][] visited) {
        Deque<Integer> xs = new ArrayDeque<>();
        Deque<Integer> ys = new ArrayDeque<>();
        xs.push(x);
        ys.push(y);
        while (!xs.isEmpty()) {
            int currX = xs.pop();
            int currY = ys.pop();
            if (currX >= 0 && currX < grid.length && currY >= 0 && currY < grid[0].length && grid[currX][currY] == '1' && !visited[currX][currY]) {
                visited[currX][currY] = true;
                for (int i = 0; i < DX.length; i++) {
                    xs.push(currX + DX[i]);
                    ys.push(currY + DY[i]);
                }
            }
        }
    }

    public static int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    search(grid, i, j, visited);
                    islands++;
                }
            }
        }
        return islands;
    }
}
