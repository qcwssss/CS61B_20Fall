package byow.Core.Map;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

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

	public ArrayList<Room> createRandomRooms(Random rand) {
		final int UPPER_LIMIT = 200;
		ArrayList<Room> listOfRooms = new ArrayList<>();
		// duplicates?

		int mapWidth = this.mapGrid.length;
		int mapHeight = this.mapGrid[0].length;

		for (int i = 0; i < UPPER_LIMIT; i++) {
			int xPos = rand.nextInt();
			int yPos = rand.nextInt();
			int width = rand.nextInt(mapWidth/4);
			int height = rand.nextInt(mapHeight/4);
		}

		return listOfRooms;
	}

	public void buildRoom(Room room) {
		int xStart = room.getXPos();
		int yStart = room.getYPos();
		int width = room.getWidth();
		int height = room.getHeight();
		buildRoom(xStart, yStart, width, height);
	}

	void buildRoom(int xStart, int yStart, int width, int height) {
		// horizontal walls
		buildLine(width, xStart, yStart, true, Tileset.WALL);
		buildLine(width, xStart, yStart + height - 1, true, Tileset.WALL);
		// vertical walls
		buildLine(height - 2, xStart, yStart + 1, false, Tileset.WALL);
		buildLine(height - 2, xStart + width - 1, yStart + 1, false, Tileset.WALL);
		// floor
		buildPlane(xStart + 1, yStart + 1, width -2, height - 2, Tileset.FLOOR);
	}

	/** Lower left corner is the starting point. */
	void buildHallWays(int length, int xStart, int yStart, boolean horizontal){
		buildLine(length, xStart, yStart, horizontal, Tileset.WALL);
		buildLine(length, xStart, yStart + 1, horizontal, Tileset.FLOOR);
		buildLine(length, xStart, yStart + 2, horizontal, Tileset.WALL);

	}


	/*
	1. Create a tile grid
	buildEmptyMap(int width, int height)
	2. Helper methods to create all kinds of rooms, hall ways, etc.
	buildWall(int length, int xStart, int yStart, int direct)
		buildLine
	buildHallWay
		L turn, T, cross turns?
	buildRoom
		buildPlane (rectangle)
	connectRoom
		isOverlap?
	 */



	private void isLineValid(int length, int xStart, int yStart, boolean horizontal) {
		checkLocation(xStart, yStart);

		int xPlus = 0, yPlus = 0;
		if (horizontal) xPlus = length - 1;
		else yPlus = length - 1;

		int xEnd = xStart + xPlus, yEnd = yStart + yPlus;
		try {
			checkLocation(xEnd, yEnd);
		} catch (IllegalArgumentException e) {
			String err = String.format("Invalid end position: (%d, %d), length out of bound!", xEnd, yEnd);
			throw new IllegalArgumentException(err);
		}
	}

	private void checkLocation(int xPos, int yPos) {
		int h = mapGrid.length;
		int w = mapGrid[0].length;
		if ( yPos > h - 1 || yPos < 0) { // y starts from 0
			throw new IllegalArgumentException("Invalid Y start position: " + yPos);
		}
		if ( xPos > w - 1 || xPos < 0) { // x starts from 0
			throw new IllegalArgumentException("Invalid X start position: " + xPos);
		}

	}

	/** The most basic unit of a map. */
	void buildLine(int length, int xStart, int yStart, boolean horizontal, TETile tile){
		isLineValid(length, xStart, yStart, horizontal);

		for (int i = 0; i < length; i++) {
			// check direction
			int x = 0, y = 0;
			if (horizontal) x = i;
			else y = i;

			this.mapGrid[xStart + x][yStart + y] = tile;
		}
	}

	/** start position locates at lower left corner. */
	private void buildPlane(int xStart, int yStart, int width, int height, TETile tile) {
		for (int i = 0; i < height; i++) {
			buildLine(width, xStart, yStart + i, true, tile);
		}
	}


	// --------------Test private methods----------------//

	/** Create a tile world of nothing. For testing private method.	 */
	static TETile[][] buildEmptyMap(int width, int height) {
		TETile[][] world = new TETile[width][height];
		for (int x = 0; x < width; x += 1) {
			for (int y = 0; y < height; y += 1) {
				world[x][y] = Tileset.NOTHING;
			}
		}
		return world;
	}


	private void testBuildPlanes() {
		this.buildPlane(5,0, 25, 10, Tileset.GRASS);
		this.buildPlane(20,10, 5, 5, Tileset.WALL);
		this.buildPlane(10,10, 6, 3, Tileset.WATER);

		//this.buildPlane(6,0, 25, 10, Tileset.GRASS);
		//this.buildPlane(25,0, 6, 10, Tileset.GRASS);
		//this.buildPlane(6,15, 10, 20, Tileset.GRASS);

	}

	public static void main(String[] args) {
		TETile[][] grid = buildEmptyMap(30 ,30);
		MapGenerator map = new MapGenerator(grid);
		//map.testLines();
		map.testBuildPlanes();


		System.out.println(TETile.toString(grid));
	}


}