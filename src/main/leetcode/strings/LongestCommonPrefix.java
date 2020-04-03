package leetcode.strings;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        longestCommonPrefix(new String[]{
                "aa",
                "a"
        });
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        char check = '\u0000';

        try {
            for (int i = 0; i < strs[0].length(); i++) {
                for (int j = 0; j < strs.length; j++) {
                    String str = strs[j];
                    if (j == 0) {
                        check = str.charAt(i);
                        sb.append(check);
                    } else if (check != str.charAt(i)) {
                        sb.deleteCharAt(sb.length() - 1);
                        return sb.toString();
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException s) {
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        return sb.toString();
    }
}
