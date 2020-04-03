package leetcode.arrays;

public class BuySellStock2 {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int acc = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int delta = prices[i+1] - prices[i];
            if (delta > 0) acc += delta;
        }

        return acc;
    }
}
