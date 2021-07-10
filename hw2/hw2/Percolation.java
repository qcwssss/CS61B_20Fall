package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] grid;
	private int size;


	/**
	 * Create N-by-N grid, with all sites initially blocked.
	 * blocked = -1; open = 0; full = 1;
	 * @param N size of grid
	 */
	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException("N must > 0");
		}
		size = N*N;
		grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = -1;
			}
		}
	}

	/* Convert a coordinate to an integer. */
	private int xyTo1D(int row, int col) {
		return grid.length * row + col;
	}

	/* Check input range. */
	private void checkRangeN(int row, int col) {
		int pos = xyTo1D(row, col);
		if (pos >= this.size || pos <= 0) {
			throw new IndexOutOfBoundsException("Position out of range");
		}
	}

	// open the site (row, col) if it is not open already
	public void open(int row, int col) {

	}
	//   public boolean isOpen(int row, int col)  // is the site (row, col) open?
	//   public boolean isFull(int row, int col)  // is the site (row, col) full?
	//   public int numberOfOpenSites()           // number of open sites
	//   public boolean percolates()              // does the system percolate?
	//   public static void main(String[] args)   // use for unit testing (not required, but keep this here for the autograder)

}
