package mergesort;

import java.util.Arrays;

/**
 * Created by Vasiliy Kylik on 26.10.2017.
 * Merging with smaller auxiliary array. Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0] to a[2∗n−1] is sorted using an auxiliary array of length n (instead of 2n)?
 */
public class MergeSmallerAuxiliaryArray {
    private void mergeWithSmallerAuxiliaryArray(Comparable[] a, Comparable[] aux, int N) {

        for (int k = 0; k < N; k++) {
            aux[k] = a[k];
        }

        int i = 0, j = N, k = 0;
        while (k < a.length) {
            if (i >= N)
                a[k++] = a[j++];
            else if (j >= a.length)
                a[k++] = aux[i++];
            else if (aux[i].compareTo(a[j]) < 0)
                a[k++] = aux[i++];
            else {
                a[k++] = a[j++];
            }
        }
    }

    public static void main(String[] args) {

        Comparable[] a = {41, 61, 70, 72, 99, 25, 51, 57, 75, 100};
        MergeSmallerAuxiliaryArray m = new MergeSmallerAuxiliaryArray();
        int N = a.length / 2;
        Comparable[] aux = new Comparable[N];
        m.mergeWithSmallerAuxiliaryArray(a, aux, N);
        System.out.println("After merging:");
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
        System.out.println();
    }
}
