package curated.array;

import java.util.*;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {

    }

    static List<String> f(String paragraph) {
        Set<String> set = new HashSet<>();
        HashMap<String, Integer> freq = new HashMap<>();
        int currMax = 0;

        for (String word : paragraph.split(" ")) {
            int currFreq = freq.getOrDefault(word, 0) + 1;
            freq.put(word, currFreq);

            if (currFreq == currMax) {
                set.add(word);
            } else if (currFreq > currMax) {
                set.clear();
                set.add(word);
                currMax = currFreq;
            }
        }

        return new ArrayList<>(set);
    }

    public static int subarraysWithKDistinct(int[] A, int K) {
        return atMost(A, K) - atMost(A, K - 1);
    }

    private static int atMost(int[] A, int K) {
        int i = 0, j = 0, ans = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        while (j < A.length) {
            int distinct = 0;
            if (freq.getOrDefault(A[j], 0) == 0) {
                distinct++;
            }
            freq.put(A[j], freq.getOrDefault(A[j], 0) + 1);
            j++;
            while (i < j && distinct > K) {
                freq.put(A[i], freq.get(A[i]) - 1); //remove left most
                if (freq.get(A[i]) == 0) {
                    distinct--;
                }
                i++;
            }
            ans += j - i;
        }
        return ans;
    }
}
