package main.datastructures.trees.heaps;

import java.util.Comparator;
import java.util.Objects;

public abstract class Heap<T> {
  private T[] array;
  private int size;
  private int maxSize;
  private Comparator<T> comparator;

  public static final int ROOT = 1; // 1 indexed array

  @SuppressWarnings("unchecked")
  public Heap(int maxSize, Comparator<T> comparator) {
    this.maxSize = maxSize;
    this.comparator = comparator;
    array = (T[]) new Object[maxSize];
  }

  public boolean insert(T t) {
    if (size >= maxSize) return false;
    array[size] = t;
    size++;

    int temp = size;

    while (greaterThan(array[parent(temp)], array[temp])) {
      swap(temp, parent(temp));
      temp = parent(temp);
    }
    return true;
  }

  public T remove() {
    T root = array[ROOT];
    array[ROOT] = array[size--];
    heapify(ROOT);
    return root;
  }

  abstract void heapify(int curr);

  public void genHeap() {
    for (int i = size / 2; i >= ROOT; i--) {
      heapify(i);
    }
  }

  boolean greaterThan(T a, T b) {
    return Objects.compare(a, b, comparator) > 0;
  }

  int parent(int curr) {
    return curr / 2;
  }

  int leftChild(int curr) {
    return curr * 2;
  }

  int rightChild(int curr) {
    return curr * 2 + 1;
  }

  boolean isLeaf(int curr) {
    return curr >= size / 2 && curr <= size;
  }

  void swap(int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
