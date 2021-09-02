package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.junit.Before;
import org.junit.Test;

import static byow.lab12.HexWorld.buildBoardWithNothing;

public class TestHexWorld {
	private HexWorld hex;
	private TERenderer ter;

	@Before
	public void setUp() {
		hex = new HexWorld();
		ter = new TERenderer();
		ter.initialize(30 ,30);
	}

	@Test
	public void testAddHexagon() {
		HexWorld hex = new HexWorld();
		TETile[][] hexBoard = buildBoardWithNothing();
		// size 3 hexagon locates at bottom left
		hex.addHexagon(3, 2, 5, hexBoard);

		//ter.renderFrame(hexBoard);

	}

	public static void main(String[] args) {
		HexWorld hex = new HexWorld();
		TERenderer ter = new TERenderer();
		ter.initialize(50, 30);

		TETile[][] hexBoard = buildBoardWithNothing();
		// size 3 hexagon locates at bottom left
		hex.addHexagon(3, 2, 5, hexBoard);

		ter.renderFrame(hexBoard);
	}

}