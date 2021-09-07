package byow.Core.Map;

import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.Point2D;

public class Room{
	private int width;
	private int height;
	private Position lowerLeft;
	private Position center;

	private Long id;


	/**
	 * Create a Room object, with position
	 * @param width w
	 * @param height h
	 * @param lowerLeft position
	 */
	public Room(int width, int height, Position lowerLeft, Long id) {
		this.width = width;
		this.height = height;
		this.lowerLeft = lowerLeft;
		this.id = id;
		this.center = calculateCenter(lowerLeft, width, height);

	}

	public Room(int width, int height, int xPos, int yPos, Long id) {
		this.width = width;
		this.height = height;
		this.lowerLeft = new Position(xPos, yPos);
		this.center = calculateCenter(this.lowerLeft, width, height);

		this.id = id;


	}

	private Position calculateCenter(Position lr, int width, int height) {
		int cenX = lr.getX() + width / 2;
		int cenY = lr.getY() + height / 2;
		return new Position(cenX, cenY);
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

	//public void setId(Long id) { this.id = id; 	}

	public Long getId() {
		return id;
	}

	public Position getCenter() {
		return center;
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
