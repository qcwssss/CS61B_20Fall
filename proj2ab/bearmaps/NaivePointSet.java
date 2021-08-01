package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
	private List<Point> listOfPoints;

	// assume points has at least size 1.
	public NaivePointSet(List<Point> points) {
		listOfPoints = points;
	}

	/**
	 * Returns the closest point to the inputted coordinates.
	 * @param x
	 * @param y
	 * @return nearest point
	 */
	@Override
	public Point nearest(double x, double y) {
		Point curMin = new Point(x, y); // pointer
		double minSqr = Point.distance(listOfPoints.get(0), curMin);
		for (Point p : listOfPoints) {
			if (Point.distance(p, curMin) < minSqr) {
				curMin = p;
			}
		}
		return curMin;
	}
}
