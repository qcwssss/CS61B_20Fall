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
 		for (int i = 0; i < length; i++) {
		    int x = 0, y = 0;
		    // check direction
		    if (horizontal) x = i;
		    else y = i;

 			this.mapGrid[xStart + x][yStart + y] = tile;
	    }
    }

    private void isLineValid(int length, int xStart, int yStart, boolean horizontal) {
	    int mapHeight = mapGrid.length;
	    int mapLen = mapGrid[0].length;
	    String message = " Hexagon is not fully on the tile board!";
	    if ( yStart > mapHeight - 1) { // y starts from 0
		    throw new IllegalArgumentException("Invalid yPos." + message);
	    }
	    if ( xStart < 0) { // x starts from 0
		    throw new IllegalArgumentException("Invalid xPos." + message);
	    }

    }

    private void checkLocation(int xStart, int yStart) {

    }


	/** Create a tile world of nothing. */
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

	    System.out.println(TETile.toString(map.mapGrid));
    }


}
