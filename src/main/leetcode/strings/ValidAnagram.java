package leetcode.strings;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] freq = new int[26];
        char[] sarr = s.toCharArray();

        for (char c : sarr) freq[c - 'a']++;

        char[] tarr = t.toCharArray();

        for (char c : tarr) {
            freq[c - 'a']--;
            if (freq[c - 'a'] < 0) return false;
        }

        return true;
    }
}
