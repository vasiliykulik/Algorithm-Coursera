package mergesort;

import java.util.Arrays;

/**
 * Created by Vasiliy Kylik on 27.10.2017.
 * Shuffling a linked list. Given a singly-linked list containing n items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlogn in the worst case.
 * Knuth shuffle.
 * In iteration i, pick integer r between i and N-1 uniformly at random,
 * or between 0 to i uniformly at random
 * Swap фхшъ and a[r]
 * <p>
 * While travelling through the array, swap the current item with a random item after current.
 */
public class ShufflingLinkedList {
    public void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = edu.princeton.cs.algs4.StdRandom.uniform(i + 1);
            swap(a, i, r);
        }
    }

    private void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public static void main(String[] args) {
        Comparable[] a = {51, 60, 42, 35, 97, 87, 51, 53, 53, 12};
        ShufflingLinkedList sf = new ShufflingLinkedList();
        sf.shuffle(a);
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
        System.out.println();
    }
}
