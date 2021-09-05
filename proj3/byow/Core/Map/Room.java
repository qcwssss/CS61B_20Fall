package byow.Core.Map;

import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.Point2D;

public class Room extends MapUnit{
	private int width;
	private int height;
	private Position lowerLeft;

	/**
	 * Create a Room object, with position
	 * @param width w
	 * @param height h
	 * @param lowerLeft position
	 */
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

	/** Getters. */
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

	/** Lower left corner is the starting point. */
	void buildRoom(int xStart, int yStart, int width, int height) {
		// horizontal walls
		buildLine(width, xStart, yStart, true, Tileset.WALL);
		buildLine(width, xStart, yStart + height - 1, true, Tileset.WALL);
		// vertical walls
		buildLine(height - 2, xStart, yStart + 1, false, Tileset.WALL);
		buildLine(height - 2, xStart + width - 1, yStart + 1, false, Tileset.WALL);
		// floor
		buildPlane(xStart + 1, yStart + 1, width -2, height - 2, Tileset.FLOOR);
	}


}
