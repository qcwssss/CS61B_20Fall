package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet{
	private List<Point> listOfPoints;

	// assume points has at least size 1.
	public NaivePointSet(List<Point> points) {
		listOfPoints = new ArrayList<>();
		listOfPoints.addAll(points);
	}

	private static double distance(double x1, double x2, double y1, double y2) {
		return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
	}

	/** Returns the closest point to the inputted coordinates. */
	@Override
	public Point nearest(double x, double y) {
		Point goal = new Point(x, y);
		Point min = null;
		double minSqr = Double.MAX_VALUE;

		for (Point p : listOfPoints) {
			double currDist = distance(p.getX(), goal.getX(), p.getY(), goal.getY());
			if (currDist < minSqr) {
				min = p;
				minSqr = currDist;
			}
		}
		return min;
	}
}
