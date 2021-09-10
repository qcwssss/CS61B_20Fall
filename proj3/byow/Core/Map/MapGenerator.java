package byow.Core.Map;

import byow.Core.Map.World.RNode;
import byow.Core.Map.World.WorldGraph;
import byow.Core.RandomUtils;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.*;

/**
 * Create by Chen.
 * Generate a map of tiles.
 *
 */
public class MapGenerator {
	private TETile[][] mapGrid;

	MapGenerator(TETile[][] grid) {
		this.mapGrid = grid;
	}

	public MapGenerator(Random random, TETile[][] grid){
		this.mapGrid = grid;
		List<Room> roomList = createRandomRooms(random, 200);
		// sort rooms based on xPos
		Collections.sort(roomList, (r1, r2)->(r1.getXPos() - r2.getXPos()));
		//System.out.println(roomList);
		for (int i = 1; i < roomList.size(); i++) {
			buildHallWays(roomList.get(i).getCenter(), roomList.get(i - 1).getCenter());
		}



	}



	public ArrayList<Room> createRandomRooms(Random rand, int UPPER_LIMIT) {
		//final int UPPER_LIMIT = 200;
		ArrayList<Room> listOfRooms = new ArrayList<>();
		// duplicates? overlap?

		int mapWidth = this.mapGrid.length;
		int mapHeight = this.mapGrid[0].length;

		for (int i = 0; i < UPPER_LIMIT; i++) {
			//int xPos = rand.nextInt(mapWidth);
			//int yPos = rand.nextInt(mapHeight);
			int xPos = (int) RandomUtils.gaussian(rand, mapWidth / 2, mapWidth/3);
			int yPos = (int) RandomUtils.gaussian(rand, mapHeight / 2, mapHeight/3);
			int width = rand.nextInt(mapWidth/8) + 5; // floor = 4-2 = 2
			int height = rand.nextInt(mapHeight/6) + 4;

			// check if lower left corner is out of bound
			if (yPos + height - 1 > mapHeight - 1 || xPos + width - 1 > mapWidth - 1) {
				continue;
			}

			Room curRoom = new Room(width, height, xPos, yPos, (long) (i+1));
			// check overlap
			if (isOverLap(curRoom, listOfRooms)) {
				continue;
			}

			try {
				buildRoom(curRoom);
			} catch (IllegalArgumentException e) {
				//System.out.println("xPos: " + xPos + ", yPos: " + yPos + ", width: " + width +  ",height : " + height);
				continue;
			}

			listOfRooms.add(curRoom);

		}
		return listOfRooms;
	}

	void connectRooms(WorldGraph wg) {
		Map<Long, RNode> rNodeMap = wg.getRodeMap();
		for (Long v : rNodeMap.keySet()) {
			RNode curRode = rNodeMap.get(v);
			for (RNode n : wg.getNeighbors().get(v)) {
				// draw path
				Position p1 = curRode.getRoom().getCenter();
				Position p2 = n.getRoom().getCenter();

				//breakTwoRoomWalls(curRode.getRoom(), n.getRoom());
				buildHallWays(p1, p2);
			}
		}
	}

	private boolean isOverLap(Room cur, List<Room> listOfRooms) {
		// check overlap
		if (listOfRooms.size() > 1) {
			for (Room r : listOfRooms) {
				if (cur.isOverlap(r)) {
					return true;
				}
			}
		}
		return false;
	}

	private void buildRoom(Room room) {
		buildRoom(room.getXPos(),  room.getYPos(), room.getWidth(), room.getHeight());
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
	void buildStraightWay(int length, int xStart, int yStart, boolean horizontal){
		if (horizontal == true) {
			buildLine(length, xStart, yStart - 1, true, Tileset.WALL);
			buildLine(length, xStart, yStart, true, Tileset.GRASS);
			buildLine(length, xStart, yStart + 1, true, Tileset.WALL);

		} else { // vertical
			buildLine(length, xStart - 1, yStart, false, Tileset.WALL);
			buildLine(length, xStart, yStart, false, Tileset.GRASS);
			buildLine(length, xStart + 1, yStart, false, Tileset.WALL);

		}

	}

	// Possible better idea:
	// Create and make 3 calls to drawLTiles method that takes a tile type as an argument.
	void buildHallWays(Position p1, Position p2){
		Position out1, out2, inner1, inner2;
		buildTurn(p1, p2, Tileset.GRASS);

		out1 = new Position(p1.getX(), p1.getY() + 1);
		out2 = new Position(p2.getX() + 1, p2.getY());
		buildTurn(out1, out2, Tileset.WALL);

		inner1 = new Position(p1.getX(), p1.getY() - 1);
		inner2 = new Position(p2.getX() - 1, p2.getY());
		buildTurn(inner1, inner2, Tileset.WALL);

		//System.out.println("p1: " + p1.toString() + "  p2: " + p2.toString());
		//System.out.println("out1: " + out1.toString() + "  out2: " + out2.toString());
		//System.out.println("inner1: " + inner1.toString() + "  inner2: " + inner2.toString());

	}

	void buildTurn(Position p1, Position p2, TETile tile){
		int pX1, pX2, pY1, pY2;
		pX1 = p1.getX();
		pY1 = p1.getY();
		pX2 = p2.getX();
		pY2 = p2.getY();

		//case 1:  move right first
		if (pX1 < pX2) {
			goRight(pX1, pX2, pY1, tile);

			if (pY1 < pY2) { // go up
				goUp(pY1, pY2, pX2, tile);
			} else { // go down
				goDown(pY1, pY2, pX2, tile);
			}
		//case 2:  move left first
		} else { // go left
			goLeft(pX1, pX2, pY1, tile);

			if (pY1 < pY2) { // go up
				goUp(pY1, pY2, pX2, tile);
			} else { // go down
				goDown(pY1, pY2, pX2, tile);
			}
		}
	}

	private void goRight(int pX1, int pX2, int pY1, TETile tile){
		for (int i = pX1; i < pX2; i++) {
			if (isNotFloor(i, pY1)) {
				this.mapGrid[i][pY1] = tile;
			}
		}
	}

	private void goLeft(int pX1, int pX2, int pY1, TETile tile){
		for (int i = pX1; i > pX2; i--) {
			if (isNotFloor(i, pY1)) {
				this.mapGrid[i][pY1] = tile;
			}
		}
	}

	private void goDown(int pY1, int pY2, int pX2, TETile tile) {
		for (int j = pY1; j >= pY2; j--) {
			if (isNotFloor(pX2, j)) {
				this.mapGrid[pX2][j] = tile;
			}
		}
	}

	private void goUp(int pY1, int pY2, int pX2, TETile tile) {
		for (int j = pY1; j <= pY2; j++) {
			if (isNotFloor(pX2, j)) {
				this.mapGrid[pX2][j] = tile;
			}
		}
	}

	private boolean isNotFloor(int xPos, int yPos) {
		return this.mapGrid[xPos][yPos] != Tileset.FLOOR;
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
			String err = String.format("Invalid end position: (%d, %d), length out of bound!", xEnd, yEnd);
			throw new IllegalArgumentException(err);
		}
	}

	private void checkLocation(int xPos, int yPos) {
		int h = mapGrid[0].length;
		int w = mapGrid.length;
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
			// from left to right, bottom to top
			if (this.mapGrid[xStart + x][yStart + y] != Tileset.FLOOR) {
				this.mapGrid[xStart + x][yStart + y] = tile;
			}
		}
		//String startToEnd =String.format("start(%d,%d),length=%d", xStart,yStart,length);
		//System.out.println(startToEnd);
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