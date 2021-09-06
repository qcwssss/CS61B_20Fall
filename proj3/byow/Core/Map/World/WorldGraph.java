package byow.Core.Map.World;

import byow.Core.Map.Path.Node;
import byow.Core.Map.Path.WeightedEdge;
import byow.Core.Map.Room;

import java.util.*;

public class WorldGraph {
	private Map<Long, RNode> rNodeMap = new HashMap<>();
	private Map<Long, Set<RNode>> neighbors = new HashMap<>();


	/** Create a WorldGraph. */
	public WorldGraph(List<Room> roomList) {
		long curId = 1;
		for (Room r: roomList) {
			RNode curNode = new RNode(r, curId);
			rNodeMap.put(r.getId(), curNode);
			curId++;
		}
		// add RNode to neighbors
		for(Long id : rNodeMap.keySet()) {
			neighbors.put(id, connectNeighbors(id, 6));
		}


		}

	private Set<RNode> connectNeighbors(Long v, int dist) {
		Set<RNode> rNodeSet = new HashSet<>();
		for(Long id : rNodeMap.keySet()) {
			if (v != id) {
				if (estimateDistance(v, id) < dist) {
					rNodeSet.add(rNodeMap.get(id));
				}
			}
		}
		return rNodeSet;

	}

	private double estimateDistance(Long start, Long goal) {
		RNode sRode = rNodeMap.get(start);
		RNode goalRode = rNodeMap.get(goal);
		int distX = sRode.getRoom().getXPos() - goalRode.getRoom().getXPos();
		int distY = sRode.getRoom().getYPos() - goalRode.getRoom().getYPos();
		double estDist = Math.sqrt(distX*distX + distY*distY);
		return estDist;

	}
}