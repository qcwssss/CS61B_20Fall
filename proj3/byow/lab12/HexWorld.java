package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;

	private static final long SEED = 2873123;
	private static final Random RANDOM = new Random(SEED);


	/** Create a tile world of nothing. */
	public static TETile[][] buildBoardWithNothing() {
		TETile[][] world = new TETile[WIDTH][HEIGHT];
		for (int x = 0; x < WIDTH; x += 1) {
			for (int y = 0; y < HEIGHT; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}
		return world;
	}

	/**
	 * Add a hexagon filled with random tiles to the world board.
	 * @param size size of hexagon
	 * @param xPos x location
	 * @param yPos y location
	 * @param board a TETile grid
	 */
	public void addHexagon(int size, int xPos, int yPos, TETile[][] board) {
		if (size < 2) {
			throw new IllegalArgumentException("size must be a positive integer larger than 1");
		}

		checkLocation(xPos, yPos, size); // need to check x, y position first
		TETile randomTile = randomTile();
		// upper half
		addUpperHalf(size, xPos, yPos, board, randomTile);
		// fill lower half, from bottom to middle
		addLowerHalf(size, xPos, yPos - 2 * size + 1, board, randomTile);

	}

	private void checkLocation(int x, int y, int size) {
		int height = size * 2;
		int lengthMax = size + 2*(size -1);
		String message = " Hexagon is not fully on the tile board!";
		if ( y < height - 1) { // y starts from 0
			throw new IllegalArgumentException("Invalid yPos." + message);
		}
		if ( x < size - 1) { // x starts from 0
			throw new IllegalArgumentException("Invalid xPos." + message);
		}
	}

	private void addUpperHalf(int size, int xPos, int yPos, TETile[][] board, TETile tile ) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size + i * 2; j++) {
				board[xPos - i + j][yPos - i] = tile;
			}
		}
	}

	private void addLowerHalf(int size, int xPos, int yPos, TETile[][] board, TETile tile ) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size + i * 2; j++) {
				board[xPos - i + j][yPos + i] = tile;
			}
		}
	}

	private static TETile randomTile() {
		int tileNum = RANDOM.nextInt(5);
		switch (tileNum) {
			case 0: return Tileset.WALL;
			case 1: return Tileset.FLOWER;
			case 2: return Tileset.TREE;
			case 3: return Tileset.WATER;
			case 4: return Tileset.MOUNTAIN;

			default: return Tileset.NOTHING;
		}
	}

	private void addColumnsOfHex(int size, int x, int y, int num, TETile[][] world) {
		for (int i = 0 ; i < num; i++) {
			addHexagon(size, x, y - size *2*i, world);
			// from top to bottom
		}
	}

	public void addTesselation(int size, int x, int y, TETile[][] board) {
		int tri = 3;
		for(int i = 0; i < 5; i++) {
			if (i < 3) {
				addColumnsOfHex(size, x + (size * 2 - 1) * i, y + size * i, tri + i, board);
			} else {
				addColumnsOfHex(size, x + (size * 2 - 1) * i, y + 2*size - size * (i - tri + 1) , 4 - i + tri, board);
			}
		}
	}

	public static void main(String[] args) {
		HexWorld hex = new HexWorld();
		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		TETile[][] hexBoard = buildBoardWithNothing();
		// size can be 2, 3, 4;
		int size = 4;
		hex.addTesselation(size, 4, HEIGHT - 3 * size  , hexBoard);

		ter.renderFrame(hexBoard);
	}
}
