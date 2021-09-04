package byow.Core.Map;

import byow.TileEngine.TETile;
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
	public void testBuildRoom() {
		map.buildRoom(0, 0, 4, 8);
		map.buildRoom(5, 0, 6, 8);

		System.out.println(TETile.toString(grid));

	}


}