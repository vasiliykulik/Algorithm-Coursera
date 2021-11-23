package a11programmingmodel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by Vasiliy Kylik (Lightning) on 26.03.2018.
 */
public class BinarySearch {
    public BinarySearch() {
    }

    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;

        }
        return -1;
    }

    public static int rank(int key, int[] a) {
        return indexOf(a, key);
    }

    public static void main(String[] args) {
        In in = new In("a11programmingmodel/tinyT.txt");
        int[] whitelist = in.readAllInts();
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.indexOf(whitelist, key) == -1)
                StdOut.println(key);
        }
    }
}
