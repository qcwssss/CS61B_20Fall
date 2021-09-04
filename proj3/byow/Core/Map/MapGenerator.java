package byow.Core.Map;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

/**
 * Create by Chen.
 * Generate a map of tiles.
 *
 */
public class MapGenerator {
	private TETile[][] mapGrid;

	public MapGenerator(TETile[][] grid) {
		this.mapGrid = grid;
	}



	/*
	1. Create a tile grid
	buildEmptyMap(int width, int height)
	2. Helper methods to create all kinds of rooms, hall ways, etc.
	buildWall(int length, int xStart, int yStart, int direct)
		buildLine
	buildHallWay
	buildRoom
		buildPlane (rectangle)
	connectRoom
		isOverlap?

	 */

	/** The most basic unit of a map. */
 	private void buildLine(int length, int xStart, int yStart, boolean horizontal, TETile tile){
 		isLineValid(length, xStart, yStart, horizontal);

 		for (int i = 0; i < length; i++) {
		    int x = 0, y = 0;
		    // check direction
		    if (horizontal) x = i;
		    else y = i;

 			this.mapGrid[xStart + x][yStart + y] = tile;
	    }
    }

    private void isLineValid(int length, int xStart, int yStart, boolean horizontal) {
	    checkLocation(xStart, yStart);

	    int xPlus = 0, yPlus = 0;
	    if (horizontal) xPlus = length - 1;
	    else yPlus = length - 1;

	    int xEnd = xStart + xPlus, yEnd = yStart + yPlus;
	    try {
		    checkLocation(xEnd, yEnd);
	    } catch (IllegalArgumentException e) {
		    throw new IllegalArgumentException("Invalid end position, length out of bound!");
	    }
    }

    private void checkLocation(int xPos, int yPos) {
	    int h = mapGrid.length;
	    int w = mapGrid[0].length;
	    if ( yPos > h - 1 || yPos < 0) { // y starts from 0
		    throw new IllegalArgumentException("Invalid Y start position");
	    }
	    if ( xPos > w - 1 || xPos < 0) { // x starts from 0
		    throw new IllegalArgumentException("Invalid X start position");
	    }

    }


	/**
	 * Create a tile world of nothing. For testing private method.
	 */
	private static TETile[][] buildEmptyMap(int width, int height) {
		TETile[][] world = new TETile[width][height];
		for (int x = 0; x < width; x += 1) {
			for (int y = 0; y < height; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}
		return world;
	}


    public static void main(String[] args) {
		TETile[][] grid = buildEmptyMap(30 ,30);
 		MapGenerator map = new MapGenerator(grid);
	    map.buildLine(9, 0, 0, true, Tileset.WATER);
	    map.buildLine(8, 0, 1, true, Tileset.WALL);
	    map.buildLine(7, 0, 2, true, Tileset.FLOWER);
	    map.buildLine(4, 0, 5, true, Tileset.GRASS);

	    map.buildLine(6, 8, 6, false, Tileset.GRASS);
	    map.buildLine(4, 7, 6, false, Tileset.WATER);
	    map.buildLine(3, 6, 6, false, Tileset.WALL);
	    map.buildLine(6, 18, 6, false, Tileset.WALL);
	    map.buildLine(6, 29, 6, false, Tileset.WALL);


	    /* Test invalid position */
	    // horizontal
	    //map.buildLine(4, 30, 5, true, Tileset.GRASS);
	    //map.buildLine(4, 4, 35, true, Tileset.GRASS);
	    //map.buildLine(4, 28, 20, true, Tileset.GRASS);

	    // vertical
	    //map.buildLine(6, 30, 6, false, Tileset.WALL);
	    map.buildLine(2, 28, 28, false, Tileset.WALL);
	    map.buildLine(3, 28, 28, false, Tileset.WALL);


	    System.out.println(TETile.toString(map.mapGrid));
    }


}
