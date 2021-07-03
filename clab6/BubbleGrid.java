import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Falling Bubbles.
 * lab6 cs61b-berkley
 * @author Chen Qiu
 *
 * A grid of bubbles.
 */
public class BubbleGrid {

	private int numOfRows;
	private int numOfColumns;
	private int[][] grid;


	/**
	 * Constructor #1.
	 * Creates a BubbleGrid.
	 */
	public BubbleGrid(int[][] grid) {
		this.grid = grid;
		numOfRows = grid.length;
		numOfColumns = grid[0].length;
	}

	/**
	 * Return bubble grid.
	 * @return this grid
	 */
	public int[][] getGrid() {
		return grid;
	}

	/**
	 * Get the union index matches the grid.
	 * @param row row index
	 * @param col col index
	 * @return union index
	 */
	private int unionIdx(int row, int col) {
		return numOfColumns * row + col;
	}

	/**
	 * A helper method to union stuck bubbles.
	 * Rule: A bubble is stuck if
	 * 	• It is in the topmost row of the grid, or
	 * 	• It is orthogonally adjacent to a bubble that is stuck.
	 * @param grid bubble grid
	 */
	private void connectBubbles(BubbleGrid grid) {
		// connect stuck bubbles
		int height = grid.numOfRows;
		int width = grid.numOfColumns;

		UnionFind union = new UnionFind(height * width);

		// store indices of topmost stuck bubbles
		int[] topmost = stuckTopmost(grid);
		// unionFind
		for (int i = 1; i <height; i ++) {
			for (int j = 0; j < width; j++) {
				if (i <= 1) {
					// loop first row, index 1
					if (topmost[j] == 1 && getGrid()[0][j] == 1) {
						union.connect(unionIdx(0,j), unionIdx(i, j));
					}
				} else {
					// connect row 2, 3, ...
					if (grid.getGrid()[i][j] == 1) {

					}
				}


			}

			for (int j : grid.getGrid()[i]) {
				if (grid.getGrid()[i][j] == 1) {
					topmost[j] = 1;
				}
			}
		}

	}

	/**
	 * A helper method to determine the status of each bubble in
	 * the Bubble grid.
	 * @param bubbleGrid instance
	 * @return int[][] stuckGrid
	 */
	private int[] stuckTopmost(BubbleGrid bubbleGrid) {
		int[] topmost = bubbleGrid.getGrid()[0];
		int[] stuckAtTop = new int[topmost.length];

		// loop the topmost row to mark stuck bubbles
		for (int i : topmost) {
			if (i == 1) {
				stuckAtTop[i] = 1;
			}
			stuckAtTop[i] = 0;
		}
		return stuckAtTop;
	}

	/*
        Returns an array whose i-th element is the number of bubbles that fall after the i-th dart is thrown.
        Assume all elements of darts are unique, valid locations in the grid.
        Must be non-destructive and have no side-effects to grid.
    */
	public int[] popBubbles(int[][] darts) {
		// remove all the bubbles will be hit by darts
		// count size
		// add bubbles back in reverse order
		// count size
		// difference = size_original - size_bubble_popped

		return new int[0];
	}


}
