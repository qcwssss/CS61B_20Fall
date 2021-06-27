/**
 * Falling Bubbles.
 * lab6 cs61b-berkley
 * @author Chen Qiu
 *
 * A grid of bubbles.
 */
public class BubbleGrid extends UnionFind{

	private int numOfRows;
	private int numOfColumns;
	private int[][] grid;

	/**
	 * Create a grid with certain rows and columns.
	 * @param rows number of rows
	 * @param columns number of columns
	 */
	public BubbleGrid(int rows, int columns) {
		super(columns);
		numOfRows = rows;
		numOfColumns = columns;

		grid = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			grid[i] = this.parent;
		}

	}

	/**
	 * Return bubble grid.
	 * @return this grid
	 */
	public int[][] getGrid() {
		return grid;
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

		// store indices of topmost stuck bubbles
		int[] topmost = new int[width];
		// unionFind
		for (int i = 0; i <height; i ++) {
			for (int j = 0; j < width; j++) {
				if (grid.getGrid()[i][j] == 1) {

				}
			}
		}

	}

	public int[] popBubbles(int[][] darts) {

		return new int[0];
	}
}
