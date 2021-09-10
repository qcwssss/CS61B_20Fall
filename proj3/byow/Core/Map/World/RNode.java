package byow.Core.Map.World;

import byow.Core.Map.Position;
import byow.Core.Map.Room;

import java.util.Map;

public class RNode {
	private Room room;


	public RNode(Room room) {
		this.room = room;
	}



	public Room getRoom() {
		return room;
	}
}
