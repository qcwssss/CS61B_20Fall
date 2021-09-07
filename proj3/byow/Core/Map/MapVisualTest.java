package byow.Core.Map;

import byow.Core.Map.World.WorldGraph;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static byow.Core.Engine.WIDTH;
import static byow.Core.Map.MapGenerator.buildEmptyMap;

public class MapVisualTest {
	private static final Random random = new Random(23217);
	private final int WIDTH = 80, HEIGHT = 40;


	@Test
	public void testWGraphConstructor() {
		List<Room> roomList = buildRoomList();
		WorldGraph wg = new WorldGraph(roomList);
		wg.toString();

	}

	private static void testConnectRooms() {
		int WIDTH = 80, HEIGHT = 40;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		MapGenerator map = new MapGenerator(grid);
		List<Room> roomList = map.createRandomRooms(random, 15);

		//WorldGraph wg = new WorldGraph(roomList);
		//map.connectRooms(wg);
		Position p1 = roomList.get(roomList.size() - 1).getCenter();
		Position p2 = roomList.get(roomList.size() - 2).getCenter();

		map.buildHallWays(p1, p2);

		ter.renderFrame(grid);

		System.out.println(TETile.toString(grid));

	}


	private List<Room> buildRoomList() {
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);
		MapGenerator map = new MapGenerator(grid);
		return map.createRandomRooms(random, 200);

	}


	private static void renderMap() {
		final int WIDTH = 80, HEIGHT = 40;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		MapGenerator map = new MapGenerator(grid);
		List<Room> roomList = map.createRandomRooms(random, 200);

		WorldGraph wg = new WorldGraph(roomList);
		map.connectRooms(wg);

		ter.renderFrame(grid);


		//System.out.println(TETile.toString(grid));
	}


	public static void main(String[] args) {
		renderMap();
		//testConnectRooms();

	}
}
