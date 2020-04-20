package main.leetcode.advent.week3;

public class Day19 {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    public static int search(int[] nums, int target) {
        int low = 0, hi = nums.length;
        int mid;
        while (low < hi) {
            mid = (low + hi) / 2;
            boolean midFlipped = nums[mid] < nums[0];
            boolean targetFlipped = target < nums[0];
            int num = midFlipped == targetFlipped ?
                    nums[mid] :
                    targetFlipped ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            if (num < target) {
                low = mid + 1;
            } else if (num > target) {
                hi = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
