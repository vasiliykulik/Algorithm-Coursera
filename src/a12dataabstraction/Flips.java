package a12dataabstraction;


import edu.princeton.cs.algs4.StdRandom;

public class Flips {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        for (int i = 0; i < n; i++) {
            if (StdRandom.bernoulli(0.5)) heads.increment();
            else tails.increment();
        }
        System.out.println(heads);
        System.out.println(tails);
        int delta = heads.tally() - tails.tally();
        System.out.println("delta: " + Math.abs(delta));
        System.out.println("delta: " + Math.abs(delta));
    }
}
