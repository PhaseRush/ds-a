package main.leetcode.advent.week3;

import main.datastructures.Pair;

import java.util.*;

public class Day17 {
    public static void main(String[] args) {
        System.out.println(numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        }));
    }


    private static void bfs(char[][] grid, Pair<Integer, Integer> start, Set<Pair<Integer, Integer>> dejaVu) {
        Deque<Pair<Integer, Integer>> q = new ArrayDeque<>();
        q.push(start);
        while (!q.isEmpty()) {
            Pair<Integer, Integer> curr = q.pop();
            if (!dejaVu.contains(curr)) {
                dejaVu.add(curr);
                if (curr.first > 0 && grid[curr.first - 1][curr.second] == '1') {
                    Pair<Integer, Integer> next = new Pair<>(curr.first - 1, curr.second);
                    if (!dejaVu.contains(next)) q.push(next);
                }
                if (curr.first < grid.length - 1 && grid[curr.first + 1][curr.second] == '1') {
                    q.push(new Pair<>(curr.first + 1, curr.second));
                }
                if (curr.second > 0 && grid[curr.first][curr.second - 1] == '1') {
                    q.push(new Pair<>(curr.first, curr.second - 1));
                }
                if (curr.second < grid[0].length - 1 && grid[curr.first][curr.second + 1] == '1') {
                    q.push(new Pair<>(curr.first, curr.second + 1));
                }
            }
        }
    }

    public static int numIslands(char[][] grid) {
        Set<Pair<Integer, Integer>> dejaVu = new LinkedHashSet<>();
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Pair<Integer, Integer> currPair = new Pair<>(i, j);
                if (!dejaVu.contains(currPair) && grid[i][j] == '1') {
                    bfs(grid, currPair, dejaVu);
                    islands++;
                }
            }
        }
        return islands;
    }


}
