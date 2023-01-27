package main.leetcode.misc;

import java.util.LinkedHashMap;

public class LRUCache {
    private final int capacity;
    private final LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity);
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
        return val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key, value);
            return;
        }
        if (cache.size() == capacity) {
            int oldestKey = cache.entrySet().iterator().next().getKey();
            cache.remove(oldestKey);
        }
        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
