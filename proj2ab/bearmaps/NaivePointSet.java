package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
	private List<Point> listOfPoints;

	// assume points has at least size 1.
	public NaivePointSet(List<Point> points) {
		listOfPoints = points;
	}

	private static double distance(double x1, double x2, double y1, double y2) {
		return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
	}

	/**
	 * Returns the closest point to the inputted coordinates.
	 * @param x
	 * @param y
	 * @return nearest point
	 */
	@Override
	public Point nearest(double x, double y) {
		double minDist = Integer.MAX_VALUE;
		Point thispoint = null;
		for (Point point : listOfPoints) {
			double currDist = NaivePointSet.distance(x, point.getX(), y, point.getY());
			if (currDist < minDist) {
				minDist = currDist;
				thispoint = point;
			}
		}
		return thispoint;
	}
}
