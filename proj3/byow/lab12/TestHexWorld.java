package byow.lab12;

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
		for (int i = 2; i < 6; i++) {
			boolean[][] row = h.addHexagon(i);
			printGrid(h.addHexagon(i));
		}
	}
}