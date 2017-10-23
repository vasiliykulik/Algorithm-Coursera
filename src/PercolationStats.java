import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by Vasiliy Kylik on 08.10.2017.
 */
public class PercolationStats {
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    private double[] est;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("n or trials expected >0 " + n + " " + trials);
        est = new double[trials];
        for (int k = 0; k < trials; k++) {
            Percolation percolation = new Percolation(n);
            double count = 0;
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (percolation.isOpen(i, j)) continue;
                percolation.open(i, j);
                count++;
            }
            est[k] = count / (n * n);
        }
        mean = StdStats.mean(est);
        stddev = StdStats.stddev(est);
        confidenceLo = mean - (1.96 * stddev) / Math.sqrt(trials);
        confidenceHi = mean + (1.96 * stddev) / Math.sqrt(trials);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, t);
        StdOut.println(" mean = " + percolationStats.mean());
        StdOut.println(" stdddev = " + percolationStats.stddev());
        StdOut.println(" 95% confidence interval= " + percolationStats.confidenceLo + ", " + percolationStats.confidenceHi);
    }
}
