package byow.Core.Map.World;

import byow.Core.Map.Position;
import byow.Core.Map.Room;

public class RNode {
	private int id;
	//private Position center;
	private Room room;

	public RNode(Room room) {
		this.room = room;
		//this.center = room.getLRPosition();
	}

	public void setId(int id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}
}
