package main.datastructures.trees.heaps;

import java.util.Comparator;

public class MinHeap<T> extends main.datastructures.trees.heaps.Heap<T> {
  private T[] array;
  private int size;
  private int maxSize;
  private Comparator<T> comparator;

  public MinHeap(int maxSize, Comparator<T> comparator) {
    super(maxSize, comparator);
  }

  void heapify(int curr) {
    // if not leaf and greater
    if (!isLeaf(curr)
            && (greaterThan(array[curr], array[leftChild(curr)])) // greater than left child
        || greaterThan(array[curr], array[rightChild(curr)])) { // greater than right child
      if (!greaterThan(array[leftChild(curr)], array[rightChild(curr)])) {
        swap(curr, leftChild(curr));
        heapify(leftChild(curr));
      } else {
        swap(curr, rightChild(curr));/**/
        heapify(rightChild(curr));
      }
    }
  }
}
