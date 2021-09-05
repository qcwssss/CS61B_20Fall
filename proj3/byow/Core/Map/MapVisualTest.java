package byow.Core.Map;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

import java.util.Random;

import static byow.Core.Map.MapGenerator.buildEmptyMap;

public class MapVisualTest {
	static final Random random = new Random(23212);

	public static void main(String[] args) {
		final int WIDTH = 60, HEIGHT = 35;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		MapGenerator map = new MapGenerator(grid);
		map.createRandomRooms(random);

		ter.renderFrame(grid);


		System.out.println(TETile.toString(grid));
	}
}
