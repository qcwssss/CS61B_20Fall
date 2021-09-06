package byow.Core.Map.World;

import byow.Core.Map.Path.Node;
import byow.Core.Map.Path.WeightedEdge;
import byow.Core.Map.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorldGraph {
	private Map<Long, RNode> nodes = new HashMap<>();


	/** Create a WorldGraph. */
	public WorldGraph(List<Room> roomList) {
		for (Room r: roomList) {
			RNode curNode = new RNode(r, 1);
			nodes.put(r.getId(), curNode);
		}
	}
}
