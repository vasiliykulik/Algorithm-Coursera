package a11programmingmodel;

/*
Sattolo's algorithm. Write a program Sattolo.java that generates
a unifomly distributed cycle of length N using Sattolo's algorithm.
%  echo 0 1 2 3 4 | java e1_1_w1_Sattolos_algorithm

 */


import edu.princeton.cs.algs4.StdIn;

public class e1_1_w1_Sattolos_algorithm {

    // this class should not be instantiated
    private e1_1_w1_Sattolos_algorithm() {}

    public static void cycle(Object[] a) {
        int n = a.length;
        for (int i = n; i > 1; i--) {
            // choose index uniformly in [0, i-1]
            int r = (int) (Math.random()*(i-1));
            Object swap = a[r];
            a[r] = a[i-1];
            a[i-1] = swap;
        }
    }

    public static void main(String[] args) {
        String [] a = StdIn.readAllStrings();

        e1_1_w1_Sattolos_algorithm.cycle(a);

        for (int i=0; i<a.length;i++){
            System.out.println(a[i]);
        }
    }
}
