package main.leetcode.advent.week2;

import java.util.*;

public class Day12 {
    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
    }

    public static int lastStoneWeight(int[] stones) {
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            pq.add(stone);
        }
        while (!pq.isEmpty()) {
            int s1 = pq.poll();
            if (pq.isEmpty()) {
                return s1;
            } else {
                int s2 = pq.poll();
                pq.add(Math.abs(s1 - s2));
            }
        }

        return -1;
    }
}
