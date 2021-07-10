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

	/**
	 *  Convert a coordinate to an integer.
	 *  WeightedQuickUnion.union(int p, int q) only takes 2 INTEGERS!
	 */
	private int xyTo1D(int row, int col) {
		return grid.length * row + col;
	}

	/* Check input range. */
	private void checkRangeN(int row, int col) {
		boolean inRange = row < grid.length && row >= 0 && col >= 0 && col < grid[0].length;
		if (!inRange) {
			throw new IndexOutOfBoundsException("Position out of range!");
		}
	}

	/* Set cell to open, blocked, or full.	 */
	private void setGrid(int row, int col, int state) {
		grid[row][col] = state;
	}

	/* 	Get the cell status. */
	public int getStatus(int row, int col) {
		return grid[row][col];
	}

	// open the site (row, col) if it is not open already
	public void open(int row, int col) {
		checkRangeN(row, col);
		setGrid(row, col, 0);

	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		return getStatus(row,col) == 0;
	}

	//   public boolean isFull(int row, int col)  // is the site (row, col) full?
	//   public int numberOfOpenSites()           // number of open sites
	//   public boolean percolates()              // does the system percolate?
	//   public static void main(String[] args)   // use for unit testing (not required, but keep this here for the autograder)

}
