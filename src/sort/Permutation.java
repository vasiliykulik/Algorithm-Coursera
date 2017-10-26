package sort;

import mergesort.Merge;

/**
 * Created by Vasiliy Kylik on 24.10.2017.
 * Permutation. Given two integer arrays of size n, design a subquadratic algorithm to determine whether one is a permutation of the other. That is, do they contain exactly the same entries but, possibly, in a different order.
 */
public class Permutation {
  private Integer[] a;
  private Integer[] b;

  public Permutation(Integer[] a, Integer[] b) {
    this.a = a;
    this.b = b;
  }

  public boolean isPermuted(Integer[] a, Integer[] b) {
    if (a.length != b.length) return false;
    Merge.sort(a);
    Merge.sort(b);
    for (int i = 0; i < a.length; i++) {
      if (!a[i].equals(b[i])) return false;
    }
    return true;
  }
}
