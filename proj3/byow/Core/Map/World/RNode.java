package byow.Core.Map.World;

import byow.Core.Map.Position;
import byow.Core.Map.Room;

import java.util.Map;

public class RNode {
	private long id;
	//private Position center;
	//private Map<Long, RNode> rnodes;

	private Room room;

	public RNode(Room room, long id) {
		this.room = room;
		//this.center = room.getLRPosition();
	}



	public Room getRoom() {
		return room;
	}
}
