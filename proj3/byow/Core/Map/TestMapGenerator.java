package byow.Core.Map;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.junit.Before;
import org.junit.Test;

import static byow.Core.Map.MapGenerator.buildEmptyMap;

/**
 * A visual test for MapGenerator.
 * Created by Chen.
 */
public class TestMapGenerator {

	TETile[][] grid;
	MapGenerator map;

	/*
	Test public methods of build
	Hallway,
	room,
	L turn,
	T turn,
	Cross


	 */

	@Before
	public void setUp() {
		grid = buildEmptyMap(30 ,30);
		map = new MapGenerator(grid);
	}

	@Test
	public void testLines() {
		map.buildLine(9, 0, 0, true, Tileset.WATER);
		map.buildLine(8, 0, 1, true, Tileset.WALL);
		map.buildLine(7, 0, 2, true, Tileset.FLOWER);
		map.buildLine(4, 0, 5, true, Tileset.GRASS);

		map.buildLine(6, 8, 6, false, Tileset.GRASS);
		map.buildLine(4, 7, 6, false, Tileset.WATER);
		map.buildLine(3, 6, 6, false, Tileset.WALL);
		map.buildLine(6, 18, 6, false, Tileset.WALL);
		map.buildLine(6, 29, 6, false, Tileset.WALL);
		map.buildLine(2, 28, 28, false, Tileset.WALL);

		/* Test invalid position */
	    /* horizontal
	    //map.buildLine(4, 30, 5, true, Tileset.GRASS);
	    //map.buildLine(4, 4, 35, true, Tileset.GRASS);
	    //map.buildLine(4, 28, 20, true, Tileset.GRASS);
	    // vertical
	    //map.buildLine(6, 30, 6, false, Tileset.WALL);
	    //map.buildLine(3, 28, 28, false, Tileset.WALL);
		*/
	}

	@Test
	public void testBuildRoom() {
		map.buildRoom(0, 0, 4, 8);
		map.buildRoom(5, 0, 6, 8);


		System.out.println(TETile.toString(grid));

	}

	@Test
	public void strangeExceptionsOfBuildRoom() {
		final int WIDTH = 60, HEIGHT = 35;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		MapGenerator map = new MapGenerator(grid);
		map.buildRoom(40, 12, 7, 5);

		ter.renderFrame(grid);

		//System.out.println(TETile.toString(grid));

	}

	@Test (expected = IllegalArgumentException.class)
	public void testExceptionOfBuildRoom() {
		map.buildRoom(20, 10, 11, 8);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testExceptionOfBuildRoom2() {
		map.buildRoom(20, 25, 10, 8);
	}

	@Test
	public void testHorizontalHallWays() {
		map.buildHallWays(10, 0, 0, true);
		map.buildHallWays(8, 0, 3, true);
		map.buildHallWays(10, 0, 27, true);

		System.out.println(TETile.toString(grid));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testHallWayExceptionHorizon1() {
		map.buildHallWays(10, 0, 28, true);

	}

}