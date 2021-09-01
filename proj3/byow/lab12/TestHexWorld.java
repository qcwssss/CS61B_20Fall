package byow.lab12;

import byow.TileEngine.Tileset;
import org.junit.Test;

public class TestHexWorld {

	public void printGrid(boolean[][] grid) {
		for (boolean[] row : grid) {
			HexWorld.printRow(row);
		}
		System.out.println("");

	}

	@Test
	public void testAddHexagon() {
		HexWorld h = new HexWorld();
		h.addHexagon(2, 1, 1, Tileset.FLOWER);

	}

	@Test
	public void testConstructor() {
		HexWorld w = new HexWorld();

	}
}