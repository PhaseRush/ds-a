import main.datastructures.lists.BloomFilter;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BloomFilterTest {
  int elements = 1_000000;
  int bitsize = 10_000000;
  BloomFilter<Integer> filter;
  // BloomFilterJ filter;
  Random prng;
  ThreadMXBean bean;

  public static void main(String[] args) {
    BloomFilterTest bloomFilterTest = new BloomFilterTest();
    if (args.length >= 1) bloomFilterTest.elements = Integer.parseInt(args[0]);
    if (args.length >= 2) bloomFilterTest.bitsize = Integer.parseInt(args[1]);
    bloomFilterTest.testCorrectness();
    bloomFilterTest.testInsertion();
    bloomFilterTest.testQuery();
  }

  public BloomFilterTest() {
    System.out.format(
        "Testing a bloom filter containing n=%d elements"
            + " in a bit array of m=%d bits (=%.1fMib) \n\n",
        elements, bitsize, ((float) bitsize / (1024 * 1024 * 8)));
    bean = ManagementFactory.getThreadMXBean();
    prng = new Random();
    prng.setSeed(0);
    filter = new BloomFilter<>(elements, bitsize);
  }

  public void testCorrectness() {
    System.out.println(
        "Testing correctness.\n" + "Creating a Set and filling it together with our filter...");
    filter.clear();
    Set<Integer> inside = new HashSet<>((int) (elements / 0.75));
    while (inside.size() < elements) {
      int v = prng.nextInt();
      inside.add(v);
      filter.add(v);
      assert filter.contains(v) : "There should be no false negative";
    }

    // testing
    int found = 0, total = 0;
    double rate = 0;
    while (total < elements) {
      int v = prng.nextInt();
      if (inside.contains(v)) continue;
      total++;
      found += filter.contains(v) ? 1 : 0;

      rate = (float) found / total;
      if (total % 1000 == 0 || total == elements) {
        System.out.format(
            "\rElements incorrectly found to be inside: %8d/%-8d (%3.2f%%)",
            found, total, 100 * rate);
      }
    }
    System.out.println("\n");

    double ln2 = Math.log(2);
    double expectedRate = Math.exp(-ln2 * ln2 * bitsize / elements);
    assert rate <= expectedRate * 1.10 : "error rate p = e^(-ln2^2*m/n)";
  }

  public void testInsertion() {
    System.out.println("Testing insertion speed...");

    filter.clear();
    long start = bean.getCurrentThreadCpuTime();
    for (int i = 0; i < elements; i++) filter.add(prng.nextInt());
    long end = bean.getCurrentThreadCpuTime();
    long time = end - start;

    System.out.format(
        "Inserted %d elements in %d ns.\n" + "Insertion speed: %g elements/second\n\n",
        elements, time, elements / (time * 1e-9));
  }

  public void testQuery() {
    System.out.println("Testing query speed...");

    filter.clear();
    for (int i = 0; i < elements; i++) filter.add(prng.nextInt());

    boolean xor = true; // Make sure our result isn’t optimized out
    long start = bean.getCurrentThreadCpuTime();
    for (int i = 0; i < elements; i++) xor ^= filter.contains(prng.nextInt());
    long end = bean.getCurrentThreadCpuTime();
    long time = end - start;

    System.out.format(
        "Queried %d elements in %d ns.\n" + "Query speed: %g elements/second\n\n",
        elements, time, elements / (time * 1e-9));
  }
}
