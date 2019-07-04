package stable_marriage;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.*;

public class StableMarriage {
  static final Random RANDOM =
      new Random("Someone get food with me plz".hashCode()); // use seed for reproducibility

  public static void main(String[] args) {
    List<Person> men = init(3, "m");
    List<Person> women = init(3, "w");

    // hardcoded test (same as in the python example posted by @Mitchell Burton)
    men.get(0).prefs = new LinkedList<>(asList(women.get(0), women.get(1), women.get(2)));
    men.get(1).prefs = new LinkedList<>(asList(women.get(1), women.get(2), women.get(0)));
    men.get(2).prefs = new LinkedList<>(asList(women.get(2), women.get(0), women.get(1)));
    women.get(0).prefs = new LinkedList<>(asList(men.get(0), men.get(1), men.get(2)));
    women.get(1).prefs = new LinkedList<>(asList(men.get(1), men.get(2), men.get(0)));
    women.get(2).prefs = new LinkedList<>(asList(men.get(2), men.get(0), men.get(1)));

    System.out.println("Hardcoded Test:");
    prettyPrint(stableMarriage(men));

    // re-init lists because function modifies the people (assigns engagedTo)
    final int n = 30; // CHANGE THIS NUMBER FOR DIFFERENT SIZE TEST CASES
    men = init(n, "m");
    women = init(n, "w");

    System.out.println("Random Test:");
    List<Person> shuffledMen = new ArrayList<>(men); // these two temp lists is my lazy way of
    List<Person> shuffledWomen = new ArrayList<>(women); // emulating python's random.sample
    for (int i = 0; i < n; i++) {
      Collections.shuffle(shuffledMen, RANDOM);
      Collections.shuffle(shuffledWomen, RANDOM);
      men.get(i).prefs = new LinkedList<>(shuffledWomen);
      women.get(i).prefs = new LinkedList<>(shuffledMen);
      System.out.println(
          String.format(
              "%s: [%s]\t%s: [%s]",
              men.get(i),
              men.get(i).prefs.stream().map(p -> p.id).collect(Collectors.joining(" ")),
              women.get(i),
              women.get(i).prefs.stream().map(p -> p.id).collect(Collectors.joining(" "))));
    }
    prettyPrint(stableMarriage(men));
  }
  /**
   * generates stable pairs of men -> women note: we do not need the women for this function because
   * all the information regarding women can be generated / found from the men
   *
   * @param men input list of men
   * @return Bi directional hashmap of men -> women
   */
  private static Map<Person, Person> stableMarriage(List<Person> men) {
    final Map<Person, Person> pairs =
        new HashMap<>(); // BiHashMap representing couples. Keys are men
    final LinkedList<Person> todo = new LinkedList<>(men);

    while (!todo.isEmpty()) {
      Person man = todo.pop();

      while (!man.prefs.isEmpty()) {
        Person woman = man.prefs.pop();
        if (woman.engagedTo == null) { // not engaged
          man.engagedTo = woman;
          woman.engagedTo = man;
          pairs.put(man, woman);
        } else if (woman.prefRank(man) < woman.prefRank(woman.engagedTo)) {
          Person otherMan = woman.engagedTo;

          otherMan.engagedTo = null; // set other man to not engaged and push to unengaged
          todo.addLast(otherMan);
          pairs.remove(otherMan);

          man.engagedTo = woman; // set the new pair
          woman.engagedTo = man;
          pairs.put(man, woman);
        }

        if (man.engagedTo != null) break; // man engaged
      }
    }

    return pairs;
  }

  /**
   * Util function for printing generated pairs
   *
   * @param pairs input map
   */
  private static void prettyPrint(Map<Person, Person> pairs) {
    System.out.println("size: " + pairs.size());
    pairs.forEach((k, v) -> System.out.print(String.format("(%s : %s)\t", k, v)));
    System.out.println();
  }

  /**
   * Util function for initializing lists of people
   *
   * @param n desired size
   * @param c prefix character
   * @return List of Person, each person having id = "c" + ${idx}
   */
  private static List<Person> init(int n, String c) {
    return IntStream.range(0, n).mapToObj(i -> new Person(c + i)).collect(Collectors.toList());
  }

  /**
   * Class to represent a Person. Lots of liberty / shortcuts taken here to make code short and
   * simple
   */
  static class Person {
    final String id; // includes gender and "index"
    LinkedList<Person> prefs; // need linked list because want pop and indexof
    Person engagedTo;

    Person(String id) {
      this.id = id;
    }

    int prefRank(Person other) {
      return prefs.indexOf(other);
    }

    @Override
    public String toString() {
      return id;
    }
  }
}
