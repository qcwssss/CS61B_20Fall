package byow.Core.Map.World;

import byow.Core.Map.Position;
import byow.Core.Map.Room;

import java.util.Map;

public class RNode {
	private long id;
	private Room room;

	public long getId() {
		return id;
	}

	public RNode(Room room, long id) {
		this.room = room;
		this.id = id;
		//this.center = room.getLRPosition();
	}



	public Room getRoom() {
		return room;
	}
}
