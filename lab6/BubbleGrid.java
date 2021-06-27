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
	 * Constructor #2.
	 * Create a grid with certain rows and columns.
	 * @param rows number of rows
	 * @param columns number of columns
	 */
	public BubbleGrid(int rows, int columns) {
		numOfRows = rows;
		numOfColumns = columns;
		this.grid = createGrid(rows, columns);
	}

	/**
	 * A helper method, build a grid with certain rows and columns.
	 * @param rows number of rows
	 * @param columns number of columns
	 */
	private int[][] createGrid(int rows, int columns) {
		return new int[rows][columns];
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}
		// start compare
		BubbleGrid other = (BubbleGrid)o;
		if (this.numOfColumns != other.numOfColumns) {
			return false;
		}
		if (this.numOfRows != other.numOfRows) {
			return false;
		}

		return Arrays.equals(this.getGrid(), other.getGrid());

	}

	@Override
	public int hashCode() {
		int result = Objects.hash(numOfRows, numOfColumns);
		result = 31 * result + Arrays.hashCode(grid);
		return result;
	}
}
