package mergesort;


import edu.princeton.cs.algs4.Insertion;

/**
 * Created by Vasiliy Kylik on 21.10.2017.
 */
//N logN
public class Merge {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid); // -ea enable assertions -da disable default
        assert isSorted(a, mid + 1, hi);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            Insertion.sort(a, lo, hi);// Insertion sort more efficiency for small arrays (1)
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        if (!less(a[mid + 1], a[mid])) return;// (2) for cases when partially sorted, test if already sorted
        merge(a, aux, lo, mid, hi);
    }


    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length]; // important not to create in recursion
        sort(a, aux, 0, a.length - 1);
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

}
