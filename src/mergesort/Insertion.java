package mergesort;

/**
 * Created by Vasiliy Kylik on 23.10.2017.
 */
public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for(int i=0; i<N;i++)
            for (int j=i; j>0 && less(a[j], a[j-1]);j--)
                exch(a,j,j-1);
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
