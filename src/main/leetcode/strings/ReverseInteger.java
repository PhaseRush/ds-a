package leetcode.strings;

// https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/880/
public class ReverseInteger {
    public int reverse(int x) {
        boolean negative = x < 0;
        x = (x < 0) ? -x : x;
        char[] s = String.valueOf(x).toCharArray();
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
        try {
            return negative ? -Integer.parseInt(new String(s)) : Integer.parseInt(new String(s));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
