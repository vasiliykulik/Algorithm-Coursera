package quicksort;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

/**
 * Created by Vasiliy Kylik on 29.10.2017.
 */
public class QuickSort {
    private static final int CUTOFF = 10;

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo])) // find item on left to swap
                if (i == hi) break;
            while (less(a[lo], a[--j])) // find item on right to swap
                if (j == lo) break;

            if (i >= j) break;  // check if pointers cross
            exch(a, i, j);
        }
        exch(a, lo, j); // swap with partitioning item
        return j; // return index of item now known to be in place
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a); // shuffle needed for performance guarantee (stay tuned)
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        //  if (hi <= lo) return;
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);  // InsertionSort for tiny subArrays (First Improvement)
            return;
        }
/*    int m = medianOf3(a, lo, lo + (hi - lo)/2, hi); // ~ 12/7 N ln N compares (slightly fewer) ~ 12/35 N ln N exchanges (slightly more)
    swap(a, lo, m);*/
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // -----------------------------------------------
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
    }


}
