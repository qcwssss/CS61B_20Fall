package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.Iterator;

public class Percolation extends WeightedQuickUnionUF {
	private int[][] grid;
	private int openSize;
	private ArrayList<Integer> sitesOpen;


	/**
	 * Create N-by-N grid, with all sites initially blocked, except row 1.
	 * blocked = -1; open = 0; full = 1;
	 * @param N size of grid
	 */
	public Percolation(int N) {
		super(N*N);

		if (N <= 0) {
			throw new IllegalArgumentException("N must > 0");
		}
		openSize = 0;
		grid = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == 0) {
					grid[i][j] = 1;
				} else {
					grid[i][j] = -1;
				}
			}
		}
		// initialize array sitesOpen to null
		sitesOpen = new ArrayList<>(N*N);
		for (int i = 0; i < N*N; i++) {
			sitesOpen.add(-1);
		}

	}

	/**
	 *  Convert a coordinate to an integer.
	 *  WeightedQuickUnion.union(int p, int q) only takes 2 INTEGERS!
	 */
	public int xyTo1D(int row, int col) {
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

	/**
	 * Open the site (row, col) if it is not open already.
	 *
	 */
	public void open(int row, int col) {
		checkRangeN(row, col);
		//can't open a full site
		if (isOpen(row, col) || isFull(row, col)) {
			return;
		}

		setGrid(row, col, 0);

		// store opened sites
		Iterator<Integer> sitesItr = this.sitesOpen.iterator();
		while(sitesItr.hasNext()) {
			sitesOpen.add(xyTo1D(row,col));
		}
		// connect
		openSize++;
		connectOpen();

	}

	/*	Connect 2 open sites. */
	private void connectOpen() {
		if (numberOfOpenSites() > 1) {
			// see if adjacent
			int prev = sitesOpen.get(openSize - 2);
			int cur = sitesOpen.get(openSize - 1);
			int diff = Math.abs(prev - cur) ;
			if (diff == 1 || diff == grid.length) {
				union(prev, cur);
			}
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		return getStatus(row,col) == 0;
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col)   {
		return getStatus(row, col) == 1;
	}

	// number of open sites
	public int numberOfOpenSites() {
		return openSize;
	}

	// does the system percolate?
	public boolean percolates() {
		return false;
	}
	//   public static void main(String[] args)   // use for unit testing (not required, but keep this here for the autograder)

}
