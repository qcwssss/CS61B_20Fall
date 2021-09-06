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
	private List<Room> buildRoomList() {
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);
		MapGenerator map = new MapGenerator(grid);
		return map.createRandomRooms(random);

	}


	public void main(String[] args) {
		//final int WIDTH = 80, HEIGHT = 40;
		TETile[][] grid = buildEmptyMap(WIDTH ,HEIGHT);

		TERenderer ter = new TERenderer();
		ter.initialize(WIDTH, HEIGHT);

		MapGenerator map = new MapGenerator(grid);
		List<Room> roomList = map.createRandomRooms(random);

		WorldGraph wg = new WorldGraph(roomList);

		ter.renderFrame(grid);


		//System.out.println(TETile.toString(grid));
	}
}
