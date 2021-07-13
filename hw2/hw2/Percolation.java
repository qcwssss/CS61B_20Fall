package hw2;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.Iterator;

public class Percolation {
	private int length;
	private int numOfSites;
	private int[][] grid;
	private int openSize;
	//private int[] sitesOpen;

	private WeightedQuickUnionUF unionUF;
	private int virtualTop;
	private int virtualBottom;



	/**
	 * Create N-by-N grid, with all sites initially blocked, except row 1.
	 * blocked = -1; open = 0;
	 * -------------------------------------
	 *  --- full = 1; ---- no longer valid
	 * @param N size of grid
	 */
	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException("N must > 0");
		}
		length = N;
		numOfSites = N*N;

		unionUF = new WeightedQuickUnionUF(numOfSites + 2);
		virtualTop = numOfSites;
		virtualBottom = numOfSites + 1;

		openSize = 0;
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
	public int xyTo1D(int row, int col) {
		return length * row + col;
	}

	public int[] OneDtoXY (int site) {
		if (site < 0 || site > numOfSites - 1) {
			throw new IllegalArgumentException("site out of range");
		}
		int row = site / length;
		int col = site % length; // remainder
		int[] coordinate = {row, col};
		return coordinate;
	}

	/* Check input range. */
	private void checkRangeN(int row, int col) {
		boolean inRange = row < length && row >= 0 && col >= 0 && col < grid[0].length;
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
		checkRangeN(row, col);
		return grid[row][col];
	}

	/**
	 * Open the site (row, col) if it is not open already.
	 *
	 */
	public void open(int row, int col) {
		checkRangeN(row, col);
		//can't open a full/open site
		if (isOpen(row, col) || isFull(row, col)) {
			return;
		}
		setGrid(row, col, 0);

		// connect
		openSize++;
		connectOpen(row, col);

	}

	/*	Connect 2 open sites. */
	private void connectOpen(int row, int col) { // current site
		checkRangeN(row,col);
		// see if adjacent 4 sites are open
		// or full
		int cur = xyTo1D(row, col);

		// union virtual head and tail
		if (row == 0) {
			unionUF.union(virtualTop, cur);
		}
		else if (row == length - 1) {
			unionUF.union(virtualBottom, cur);
		}

		// left
		if ( (col - 1 > 0) && isOpen(row, col -1)) {
			unionUF.union(cur, xyTo1D(row, col -1));
		}
		// right
		if ( (col + 1 < length) && isOpen(row, col + 1)) {
			unionUF.union(cur, xyTo1D(row, col  + 1));
		}
		// up
		if ((row -1 >= 0) && isOpen(row - 1, col)) {
			unionUF.union(cur, xyTo1D(row -1, col));
		}
		// down
		if ((row + 1 < length) && isOpen(row + 1,col)) {
			unionUF.union(cur, xyTo1D(row + 1, col));
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		checkRangeN(row, col);
		return getStatus(row,col) == 0;
	}

	public boolean isOpen(int site) {
		int[] siteXY = OneDtoXY(site);
		return getStatus(siteXY[0], siteXY[1]) == 0;
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col)  {
		checkRangeN(row, col);
		return unionUF.connected(xyTo1D(row, col), virtualTop);
	}

	// number of open sites
	public int numberOfOpenSites() {
		return openSize;
	}

	/* Is two sites connected? */
	public boolean isConnected(int p, int q) {
		return this.unionUF.connected(p, q);
	}

	// does the system percolate?
	public boolean percolates() {
		return unionUF.connected(virtualBottom, virtualTop);
	}

	public static double runtime(int N, int T) {
		Stopwatch stopwatch = new Stopwatch();
		PercolationFactory pf = new PercolationFactory();
		PercolationStats stats = new PercolationStats(N, T, pf);
		return stopwatch.elapsedTime();
	}

	// use for unit testing (not required, but keep this here for the autograder)
	public static void main(String[] args) {
		double time1 = Percolation.runtime(5, 5);
		System.out.println(time1);
	}

}
