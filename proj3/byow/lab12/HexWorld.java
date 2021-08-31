package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

	public boolean[][] addHexagon(int size) {
		if (size < 2) {
			throw new IllegalArgumentException("size must be a positive integer larger than 1");
		}
		// longest row = size + 2 * (size - 1)
		int longest = 3 * size - 2;
		int height = size * 2;
		boolean[][] hex = new boolean[height][longest];
		// fill

		return hex;

	}

	private boolean[] fillRow(int size, int type) {
		boolean[] row = new boolean[size];
		switch (type) {
			case 1:
				for (int i = 0; i < size; i++) {
					if (i >= size - 1 && i < 2 * size -1) {
						row[i] = true;
					} else {
						row[i] = false;
					}
				}
				break;
			default:
				for (int i = 0; i < size; i++) row[i] = true;
				break;
		}

		return row;
	}
}
