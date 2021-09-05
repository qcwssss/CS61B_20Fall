package byow.Core.Map;

import byow.Core.RandomUtils;
import byow.TileEngine.TETile;

import java.util.Random;

public class PointsGenerator {
	private static final long SEED = 2873123;
	private static final Random RANDOM = new Random(SEED);

	private void generatePoints(TETile[][] grid) {
		// Since 1st is x, 2nd is y
		int height = grid[0].length;
		int width = grid.length;
		//RandomUtils
	}

}
