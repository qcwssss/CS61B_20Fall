package byow.Core.Map;

public class Position {


	private int xPos;
	private int yPos;

	/**
	 * Create a position.
	 * @param xPos x coordinate of Lower Left position
	 * @param yPos y coordinate of Lower Left position
	 */
	public Position(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * Getters.
	 * @return xPos
	 */
	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
}
