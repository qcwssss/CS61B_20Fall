package byow.Core.Map;

import byow.Core.Map.World.KDTree;
import byow.Core.Map.World.WorldGraph;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static byow.Core.Map.MapGenerator.buildEmptyMap;

public class MapVisualTest {
	private static final Random random = new Random(23217);
	private final int WIDTH = 80, HEIGHT = 40;


	@Test
	public void testSortRoomList() {
		List<Room> roomList = buildRoomList();
		roomList.sort((r1, r2) -> (r1.getXPos() - r2.getXPos()));
		for (Room r : roomList) {
			System.out.println(r);
		}
	}

	@Test
	public void testGenerateWorld() {
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		MapGenerator world = new MapGenerator(random, grid);
		System.out.println(TETile.toString(grid));
	}

	@Test
	public void testConnectRooms() {
		int WIDTH = 80, HEIGHT = 40;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		MapGenerator map = new MapGenerator(grid);
		List<Room> roomList = map.createRandomRooms(random, 15);
		List<Position> centerList = new ArrayList<>(roomList.size());
		for(Room r : roomList) {
			centerList.add(r.getCenter());
		}

		Position p1 = roomList.get(roomList.size() - 1).getCenter();
		Position p2 = roomList.get(roomList.size() - 2).getCenter();

		KDTree kdtCenter = new KDTree(centerList);
		Position p3 = kdtCenter.nearest(20, 40);
		map.buildHallWays(p2, p3);
		map.buildHallWays(p1, p2);

		ter.renderFrame(grid);
		System.out.println(TETile.toString(grid));
	}


	private List<Room> buildRoomList() {
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);
		MapGenerator map = new MapGenerator(grid);
		return map.createRandomRooms(random, 200);

	}

	@Test
	public void testBuildTurns() {
		int WIDTH = 30, HEIGHT = 30;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		MapGenerator map = new MapGenerator(grid);
		Position p0 = new Position(3, 3) ;
		Position p1 = new Position(6, 6) ;

		map.buildTurn(p0, p1, Tileset.GRASS);
		map.buildTurn(p0, new Position(0 ,6), Tileset.WATER);
		map.buildTurn(p0, new Position(0 ,1), Tileset.MOUNTAIN);
		map.buildTurn(p0, new Position(6 ,1), Tileset.FLOOR);

		ter.renderFrame(grid);

		System.out.println(TETile.toString(grid));

	}

	@Test
	public void testBuildHallWays() {
		int WIDTH = 30, HEIGHT = 30;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		MapGenerator map = new MapGenerator(grid);
		Position p0 = new Position(6, 6) ;
		Position p1 = new Position(2, 10) ;
		Position p2 = new Position(10, 10) ;
		Position p3 = new Position(2, 2) ;
		Position p4 = new Position(10, 2) ;

		//map.buildHallWays(p0, p1); // correct
		map.buildHallWays(p0, p2);
		map.buildHallWays(p0, p3);
		//map.buildHallWays(p0, p4); // correct

		ter.renderFrame(grid);
		System.out.println(TETile.toString(grid));

	}


	@Test
	public void testRenderMap() {
		final int WIDTH = 80, HEIGHT = 40;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		MapGenerator map = new MapGenerator(grid);
		List<Room> roomList = map.createRandomRooms(random, 200);

		WorldGraph wg = new WorldGraph(roomList);
		map.connectRooms(wg);

		//ter.renderFrame(grid);
		System.out.println(TETile.toString(grid));
	}

	public void createWorld() {
		int WIDTH = 80, HEIGHT = 38;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		MapGenerator world = new MapGenerator(random, grid);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);
		ter.renderFrame(grid);
	}

	public static void main(String[] args) {
		MapVisualTest visual = new MapVisualTest();
		visual.createWorld();
	}
}
