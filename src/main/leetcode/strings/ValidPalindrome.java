package leetcode.strings;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^\\w]", "");

        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr[arr.length - 1 - i]) return false;
        }
        return true;
    }
}
