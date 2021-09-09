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



	/**
	 * Returns the haversine distance squared between two points, assuming
	 * x represents the longitude and y represents the latitude.
	 */
	public static double distance(Position p1, Position p2) {
		return distance(p1.getX(), p2.getX(), p1.getY(), p2.getY());
	}


	private static double distance(int xV, int xW, int yV, int yW) {
		double dSqr = Math.pow(xV - xW, 2) + Math.pow(yV - yW, 2);
		return Math.sqrt(dSqr);
	}

	@Override
	public String toString() {
		return "Position{" +
				"xPos=" + xPos +
				", yPos=" + yPos +
				'}';
	}
}
