package main.leetcode.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/881/
public class FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
    }

    public static int firstUniqChar(String s) {
        if (s.length() == 0) return -1;

        char[] arr = s.toCharArray();
        Map<Character, Integer> dejaVu = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (dejaVu.containsKey(arr[i])) {
                dejaVu.put(arr[i], 2);
            } else {
                dejaVu.put(arr[i], 1);
            }
        }
        return dejaVu.entrySet().stream().filter(e -> e.getValue() == 1).mapToInt(i -> s.indexOf(i.getKey())).min().orElse(-1);
    }
}
