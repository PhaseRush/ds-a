package main.external;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rhyme {
    static final String ALL_WORD_URL = "https://raw.githubusercontent.com/Birds/dictionary-attack/master/dictionary.txt";
    static final int SUFFIX_LENGTH = 6;

    static final Metaphone3 M3 = new Metaphone3();
    static final String QUERY_WORD = "hedge";
    static final String QUERY_ENCODED = encode(QUERY_WORD);


    public static void main(String[] args) throws IOException {
        System.out.println("Target:\t" + QUERY_ENCODED);
        List<String> candidates = new ArrayList<>();
        try (Scanner sc = new Scanner(new URL(ALL_WORD_URL).openStream(),
                StandardCharsets.UTF_8.toString())) {
            sc.useDelimiter("\n");
            int count = 0, index = 0;
            while (sc.hasNext()) {
                final String word = sc.nextLine().trim();
                final String encoded = encode(word);
                if (commonSuffix(QUERY_ENCODED, encoded).length() >= Math.min(QUERY_ENCODED.length(), SUFFIX_LENGTH)) {
                    System.out.printf("Index:%s\tCount:%s\tWord: %s\n", index, count, word);
                    candidates.add(word);
                    count++;
                }
                index++;
            }
        }
        System.out.println(candidates);
    }

    private static String encode(final String in) {
        M3.SetWord(in);
        M3.Encode();
        return M3.GetMetaph();
    }

    // https://github.com/google/guava/blob/b21d5719e6a0ce6f4e2f71eec09bf55908ede062/android/guava/src/com/google/common/base/Strings.java
    private static String commonSuffix(CharSequence a, CharSequence b) {
        int maxSuffixLength = Math.min(a.length(), b.length());
        int s = 0;
        while (s < maxSuffixLength && a.charAt(a.length() - s - 1) == b.charAt(b.length() - s - 1)) {
            s++;
        }
        if (validSurrogatePairAt(a, a.length() - s - 1)
                || validSurrogatePairAt(b, b.length() - s - 1)) {
            s--;
        }
        return a.subSequence(a.length() - s, a.length()).toString();
    }

    private static boolean validSurrogatePairAt(CharSequence string, int index) {
        return index >= 0
                && index <= (string.length() - 2)
                && Character.isHighSurrogate(string.charAt(index))
                && Character.isLowSurrogate(string.charAt(index + 1));
    }
}
