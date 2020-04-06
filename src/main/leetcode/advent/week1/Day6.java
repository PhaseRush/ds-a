package main.leetcode.advent.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args) {
        String[] in = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(in));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<Map<Integer, Long>, List<String>> seen = new HashMap<>();
        for (String str : strs) {
            Map<Integer, Long> freq = str.chars()
                    .boxed()
                    .collect(Collectors.groupingBy(i -> i,
                            Collectors.counting()));
            List<String> list = seen.getOrDefault(freq, new ArrayList<>());
            list.add(str);
            seen.put(freq, list);
        }
        return new ArrayList<>(seen.values());
    }
}
