package a12dataabstraction;

import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *  A mutable data type for an integer counter.
 *
 *  The test clients create n counters and performs trials increment
 *  operations on random counters.
 ******************************************************************************/

/**
 * The {@code Counter} class is a mutable data type to encapsulate a counter.
 */
public class Counter implements Comparable<Counter> {

    private final String name;
    private int count = 0;


    /**
     * Initializes a new counter starting at 0, with the given id.
     *
     * @param id the name of the counter
     */
    public Counter(String id) {
        name = id;
    }

    /**
     * Reads two command-line integers n and trials; creates n counters;
     * increments trials counters at random; and prints results.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt((args[1]));

        Counter[] hits = new Counter[n];
        for (int i = 0; i < n; i++) {
            hits[i] = new Counter("counter " + i);
        }

        for (int t = 0; t < trials; t++) {
            hits[StdRandom.uniform(n)].increment();
        }

        for (int i = 0; i < n; i++) {
            System.out.println(hits[i]);
        }
    }

    /**
     * Increments the counter by 1.
     */
    public void increment() {
        count++;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    /**
     * Returns the current value of this counter.
     *
     * @return the current value of this counter
     */
    public int tally() {
        return count;
    }

    @Override
    public int compareTo(Counter o) {
        if (this.count < o.count) return -1;
        else if (this.count > o.count) return +1;
        else return 0;
    }
}
