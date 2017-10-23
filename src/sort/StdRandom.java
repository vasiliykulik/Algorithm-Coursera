package sort;

/**
 * Created by Vasiliy Kylik on 18.10.2017.
 */

//Knuth shuffle
public class StdRandom {
    public static void shuffle(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = edu.princeton.cs.algs4.StdRandom.uniform(i + 1);
            exch(a, i, r);
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
