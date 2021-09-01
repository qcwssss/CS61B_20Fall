package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;

	//private TETile[][] hexWorld;
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


	public void addHexagon(int size, int xPos, int yPos, TETile[][] board) {
		if (size < 2) {
			throw new IllegalArgumentException("size must be a positive integer larger than 1");
		}

		int longest = 3 * size - 2;
		int height = size * 2;
		TETile randomTile = randomTile();

		// fill
		int indent = size - 1;
		// upper half
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size + i * 2; j++) {
				board[xPos - i + j][yPos - i] = randomTile;
			}
		}
		// lower half
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size + i * 2; j++) {
				board[xPos - i + j][yPos + i] = randomTile;
			}
		}

	}


	private void drawRow(int size, int indent, int xPos, int yPos, TETile tile, TETile[][] board) {
		// indent/col <= size - 1
		if (indent > size - 1 || indent < 0) {
			throw new IllegalArgumentException("Invalid indent");
		}

		int longest = 3 * size - 2;
		//boolean[] row = new boolean[longest];
		for (int i = xPos; i < longest; i++) {
			if (i >= indent && i < longest - indent) {
				board[yPos][i] = tile;
			}
		}

	}

	public static void printRow(boolean[] row) {
		for (boolean val : row) {
			if (val) System.out.print("a ");
			else System.out.print("_ ");
		}
		System.out.println("");
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

	public static void main(String[] args) {
		HexWorld hex = new HexWorld();
		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		TETile[][] hexBoard = buildBoardWithNothing();
		hex.addHexagon(3, 3, 1, hexBoard);

		ter.renderFrame(hexBoard);
	}
}
