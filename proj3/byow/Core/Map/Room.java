package byow.Core.Map;

import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.Point2D;

public class Room{
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

	/**
	 * Check if this Room is overlapping with other.
	 * @param other Room
	 * @return a boolean
	 */
	public boolean isOverlap(Room other) {
		boolean indicator = true;
		// if there is no overlapping on X
		if (this.getXPos() + this.getWidth() <= other.getXPos() || other.getXPos() + other.getWidth() <= this.getXPos()) {
			indicator = false;
		}
		// if there is no overlapping on Y
		if (this.getYPos() + this.getHeight() <= other.getYPos() || other.getYPos() + other.height <= this.getYPos()) {
			indicator = false;
		}
		return indicator;
	}




}
