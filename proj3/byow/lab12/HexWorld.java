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
		// need to check x, y position first
		checkLocation(xPos, yPos, size);

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

	public static void main(String[] args) {
		HexWorld hex = new HexWorld();
		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		TETile[][] hexBoard = buildBoardWithNothing();
		// size 3 hexagon locates at bottom left 
		hex.addHexagon(3, 2, 5, hexBoard);

		ter.renderFrame(hexBoard);
	}
}
