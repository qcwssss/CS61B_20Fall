package byow.Core.Map;

import edu.princeton.cs.algs4.Point2D;

public class Room {
	private int width;
	private int height;
	private Position lowerLeft;

	public Room(int width, int height, Position lowerLeft) {
		this.width = width;
		this.height = height;
		this.lowerLeft = lowerLeft;
	}

	public Room(int width, int height, int xPos, int yPos) {
		this.width = width;
		this.height = height;
		this.lowerLeft = new Position(xPos, yPos);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Position getLRPosition() {
		return lowerLeft;
	}

	public int getXPos() {
		return lowerLeft.getX();
	}

	public int getYPos() {
		return lowerLeft.getY();
	}
}
