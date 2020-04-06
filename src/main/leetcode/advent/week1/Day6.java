package main.leetcode.advent.week1;

import java.util.*;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args) {
        String[] in = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(in));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .map(str -> new AbstractMap.SimpleEntry<>(str, str))
                .map(pair -> new AbstractMap.SimpleEntry<>(pair.getKey().chars()
                        .boxed()
                        .collect(Collectors.groupingBy(i -> i,
                                Collectors.counting())),
                        pair.getValue()))
                .collect(Collectors.toMap(
                        AbstractMap.SimpleEntry::getKey,
                        entry -> new ArrayList<>(Collections.singletonList(entry.getValue())),
                        (oldList, newList) -> {
                            oldList.addAll(newList);
                            return oldList;
                        }))
                .values());
    }
}
