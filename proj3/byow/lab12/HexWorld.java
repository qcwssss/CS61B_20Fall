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
		int indent = size - 1;
		for (int i = 0; i < height/2; i++) {
			hex[i] = drawRow(size, indent);
			if (indent > 0) indent--;
			else indent = 0;
		}
		for (int i = height/2; i < height; i++) {
			hex[i] = drawRow(size, indent);
			indent++;
		}
		return hex;

	}

	private boolean[] drawRow(int size, int indent) {
		// indent/col <= size - 1
		if (size < 2) {
			throw new IllegalArgumentException("size must be a positive integer larger than 1");
		}
		if (indent > size - 1 || indent < 0) {
			throw new IllegalArgumentException("Invalid indent");
		}

		int longest = 3 * size - 2;
		boolean[] row = new boolean[longest];
		for (int i = 0; i < longest; i++) {
			if (i >= indent && i < longest - indent) {
			//if (i >= size - 1 && i < 2 * size -1) {
				row[i] = true;
			} else {
				row[i] = false;
			}
		}
		return row;

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
		boolean[] a = hex.drawRow(3, 2);
		hex.printRow(a);
	}
}
