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
	private static final int WIDTH = 60;
	private static final int HEIGHT = 30;

	private TETile[][] hexWorld;

	public HexWorld() {
		this.hexWorld = buildHexWorld();
	}

	public void addHexagon(int size, int xPos, int yPos) {

	}

	public void createHexagon(int size, int xPos, int yPos, TETile tile) {
		if (size < 2) {
			throw new IllegalArgumentException("size must be a positive integer larger than 1");
		}
		// longest row = size + 2 * (size - 1)
		int longest = 3 * size - 2;
		int height = size * 2;
		boolean[][] hex = new boolean[height][longest];
		// fill
		int indent = size - 1;
		for (int i = yPos; i < height/2; i++) {
			drawRow(size, indent, xPos, i, tile);
			if (indent > 0) indent--;
			else indent = 0;
		}
		for (int i = height/2; i < height; i++) {
			drawRow(size, indent, xPos, i, tile);
			indent++;
		}
		//return hex;

	}

	private TETile[][] buildHexWorld() {
		TETile[][] world = new TETile[WIDTH][HEIGHT];
		for (int x = 0; x < WIDTH; x += 1) {
			for (int y = 0; y < HEIGHT; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}
		return world;

	}

	private void drawRow(int size, int indent, int xPos, int yPos, TETile tile) {
		// indent/col <= size - 1
		if (size < 2) {
			throw new IllegalArgumentException("size must be a positive integer larger than 1");
		}
		if (indent > size - 1 || indent < 0) {
			throw new IllegalArgumentException("Invalid indent");
		}

		int longest = 3 * size - 2;
		//boolean[] row = new boolean[longest];
		for (int i = xPos; i < longest; i++) {
			if (i >= indent && i < longest - indent) {
				this.hexWorld[yPos][i] = tile;
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

	public static void main(String[] args) {
		HexWorld hex = new HexWorld();
		//boolean[] a = hex.drawRow(3, 2);
		//hex.printRow(a);
	}
}
