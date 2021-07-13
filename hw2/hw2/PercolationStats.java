package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	//private Percolation percolation;
	private int times; // times of experiments
	private int size; // grid size
	private double sumT;
	private double sqrDev; // square deviation

	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T, PercolationFactory pf) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("-- N and T must be positive! --");
		}
		this.times = T;
		this.size = N;


		int xT = 0; // total sum of open sites of T times
		for (int i = 0; i < times; i++) {
			Percolation percol = pf.make(size);
			while(!percol.percolates()) {
				int rand = StdRandom.uniform(0, N*N);
				if (!percol.isOpen(rand)) {
					percol.open(rand / N,rand % N);
				}
			}
			xT += percol.numberOfOpenSites();
		}
		sumT = xT;
		sqrDev = sumT / (double) times;

	}

	// sample mean of percolation threshold
	//    public double mean()

	// sample standard deviation of percolation threshold
	//   public double stddev()

	// low endpoint of 95% confidence interval
	//   public double confidenceLow()

	// high endpoint of 95% confidence interval
	//   public double confidenceHigh()


}
