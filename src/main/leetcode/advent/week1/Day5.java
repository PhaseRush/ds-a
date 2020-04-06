package main.leetcode.advent.week1;

public class Day5 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int acc = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int delta = prices[i + 1] - prices[i];
            if (delta > 0) acc += delta;
        }

        return acc;
    }
}
