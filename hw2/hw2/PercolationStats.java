package hw2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class PercolationStats {
	private int times; // times of experiments
	private int size; // grid size
	private List<Integer> xList; // threshold number of open sites

	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T, PercolationFactory pf) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("-- N and T must be positive! --");
		}
		this.times = T;
		this.size = N;

		xList = new ArrayList<Integer>(T);
		int xT = 0; // total sum of open sites of T times
		for (int i = 0; i < times; i++) {
			Percolation percol = pf.make(size);
			while(!percol.percolates() && percol.numberOfOpenSites() <= N*N) {
				int rand = StdRandom.uniform(0, N*N);
				if (!percol.isOpen(rand)) {
					percol.open(rand / N,rand % N);
				}
			}
			xList.add(percol.numberOfOpenSites());
		}

	}

	// sample mean of percolation threshold
	public double mean() {
		double sumT = 0;
		for (Integer d : xList) {
			sumT += d;
		}
		return sumT / times;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		double mean = mean();

		double sqrDevNumerator = 0;
		for (Integer d : xList) {
			sqrDevNumerator += Math.pow(d - mean, 2);
		}
		double sqrDev = sqrDevNumerator / times -1;
		return Math.pow(sqrDev, 0.5);
	}

	// low endpoint of 95% confidence interval
	public double confidenceLow() {
		return stddev() - confidentLatter();
	}

	// high endpoint of 95% confidence interval
	public double confidenceHigh() {
		return stddev() + confidentLatter();
	}

	private double confidentLatter() {
		return 1.96 * stddev() / Math.pow(times, 0.5);
	}

	// unit test
	public static void main(String[] args) {
		PercolationFactory pf = new PercolationFactory();
		PercolationStats stats = new PercolationStats(10, 30, pf);
		System.out.println(stats.mean());
	}


}
