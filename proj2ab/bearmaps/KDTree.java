package bearmaps;

import org.junit.platform.engine.support.hierarchical.Node;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTree {
	private Node root;
	//private List<Point> listOfPoints;

	private class Node {
		private Node left;
		private Node right;
		private Point p;
		private Orientation orient;

		private Node(double x, double y) {
			p = new Point(x, y);
		}

		private Node(Point point) {
			p = point;
		}

		private int compareTo(Node n1, Node n2) {
			
		}
	}

	public KDTree(List<Point> points) {
		//listOfPoints = points;
	}

	public void put(Point p) {
		if (p == null) {
			throw new IllegalArgumentException("calls put() with a null point");
		}
		root = put(root, p);
	}

	private Node put(Node x, Point point) {
		if (x == null) {
			return new Node(point);
		}

		if (x.orient == Orientation.HORIZONTAL) {

		}
	}

	// Returns the closest point to the inputted coordinates.
	// This should take \(O(\log N)\) time on average, where \(N\) is the number of points.
	public Point nearest(double x, double y) {
		return null;
	}


	public static void main(String[] args) {
		Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
		Point p2 = new Point(3.3, 4.4);
		Point p3 = new Point(-2.9, 4.2);

		KDTree kd = new KDTree(List.of(p1, p2, p3));
		Point ret = kd.nearest(3.0, 4.0); // returns p2
		double actual1 = ret.getX(); // evaluates to 3.3
		double actual2 = ret.getY(); // evaluates to 4.4
		assertEquals(p2, ret);
		assertEquals(3.3, actual1, 0.01);
		assertEquals(4.4, actual2, 0.01);
	}
}
