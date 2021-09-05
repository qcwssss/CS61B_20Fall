package byow.Core.Map;

import byow.TileEngine.Tileset;

public class HallWay extends MapUnit {

	/** Lower left corner is the starting point. */
	void buildHallWays(int length, int xStart, int yStart, boolean horizontal){
		buildLine(length, xStart, yStart, horizontal, Tileset.WALL);
		buildLine(length, xStart, yStart + 1, horizontal, Tileset.FLOOR);
		buildLine(length, xStart, yStart + 2, horizontal, Tileset.WALL);

	}
}
