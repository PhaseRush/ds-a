package aoc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Day3a {
    static Map<Character, Integer> opX = Map.of('U', 0, 'D', 0, 'R', +1, 'L', -1);
    static Map<Character, Integer> opY = Map.of('U', +1, 'D', -1, 'R', 0, 'L', 0);

    public static void main(String[] args) throws IOException {
        StringBuilder file = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(System.getProperty("user.dir") + "/src/aoc/input.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> file.append(s).append("\n"));
        }
        String[] A = file.substring(0, file.indexOf("\n")).split(",");
        String[] B = file.substring(file.indexOf("\n")).split(",");

        Map<Pair, Integer> setA = getPoints(A);
        Map<Pair, Integer> setB = getPoints(B);
        Map<Pair, Integer> intersection = new HashMap<>(setA);
        intersection.keySet().retainAll(setB.keySet());

        System.out.println(intersection.keySet().stream().map(pair -> setA.get(pair) + setB.get(pair)).min(Comparator.comparingInt(Integer::intValue)).orElseThrow());
    }

    private static Map<Pair, Integer> getPoints(final String[] commands) {
        int x = 0, y = 0, len = 0;
        Map<Pair, Integer> set = new HashMap<>();
        for (String s : commands) {
            s = s.trim();
            char c = s.charAt(0);
            int num = Integer.parseInt(s.substring(1));
            for (int i = 0; i < num; i++) {
                x += opX.get(c);
                y += opY.get(c);
                len++;
                Pair pair = Pair.of(x, y);
                if (!set.containsKey(pair)) {
                    set.put(pair, len);
                }
            }
        }
        return set;
    }

    static class Pair {
        int x, y;

        public Pair(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        static Pair of(int x, int y) {
            return new Pair(x, y);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
