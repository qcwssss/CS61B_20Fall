package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fractions;
    private int numTrials;

    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        numTrials = T;
        fractions = new double[T];
        for (int i = 0; i < T; i++) {
            int numOpened = 0;
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int r = StdRandom.uniform(N);
                int c = StdRandom.uniform(N);
                if (!p.isOpen(r, c)) {
                    p.open(r, c);
                    numOpened += 1;
                }
            }
            fractions[i] = (double) numOpened / (N * N);
        }
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(fractions);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        return mu - 1.96 * sigma / Math.sqrt(numTrials);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        return mu + 1.96 * sigma / Math.sqrt(numTrials);
    }
}
