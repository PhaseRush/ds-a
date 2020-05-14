package main.leetcode.advent.week3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day20 {
    public static void main(String[] args) {
        System.out.println(swapPairs(List.of("1", "2", "3", "4", "5")));
        Person p = new Person();
        System.out.println(p.equals(p));
    }


    public static List<String> swapPairs(final List<String> list) {
        return IntStream.range(0, list.size())
                .collect(ArrayList::new,
                        (l, i) -> {
                            if (i % 2 == 0) {
                                l.add(list.get(i));
                            } else {
                                l.add(i - 1, list.get(i));
                            }
                        },
                        (n, m) -> {
                        });
    }

    static class Person {
        String name;
        int age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (age != person.age) return false;
            return name.equals(person.name);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            return result;
        }
    }
}

