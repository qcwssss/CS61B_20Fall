package bearmaps;

import org.junit.platform.engine.support.hierarchical.Node;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTree {
	private Node root;
	//private List<Point> listOfPoints;

	private class Node {
		private Node left; // also refers to down child
		private Node right; // also refers to up child
		private Point p;
		private Orientation orient;

		private Node(double x, double y) {
			p = new Point(x, y);
		}

		private Node(Point point, Orientation to) {
			p = point;
			orient = to;
		}

	}

	public KDTree(List<Point> points) {
		for (Point p : points) {
			if (root == null) {
				root = put(root, p, Orientation.VERTICAL);
			} else {
				put(root, p, root.orient.opposite());
			}
		}
	}

	private int comparePoint(Point a, Point b, Orientation orient) {
		if (orient == Orientation.HORIZONTAL) {
			return Double.compare(a.getX(), b.getX());
		} else {
			return Double.compare(a.getY(), b.getY());
		}
	}

	private Node put(Node x, Point point, Orientation direct) {
		if (x == null) {
			return new Node(point, direct.opposite());
		}

		if (comparePoint(x.p, point, x.orient) > 0) {
			// x.point > point, add left
			x.left = put(x.left, point, x.orient);
		} else {
			x.right = put(x.right, point, x.orient);
		}
		return x;
	}

	// Returns the closest point to the inputted coordinates.
	// This should take \(O(\log N)\) time on average, where \(N\) is the number of points.
	public Point nearest(double x, double y) {
		return null;
	}


	public static void main(String[] args) {
		Point a = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
		Point b = new Point(4, 2);
		Point c = new Point(4, 5);
		Point d = new Point(3, 3);
		Point e = new Point(1, 5);
		Point f = new Point(4, 4);

		KDTree kd = new KDTree(List.of(a, b, c, d, e, f));
		/*
		Point ret = kd.nearest(3.0, 4.0); // returns p2
		double actual1 = ret.getX(); // evaluates to 3.3
		double actual2 = ret.getY(); // evaluates to 4.4
		assertEquals(p2, ret);
		assertEquals(3.3, actual1, 0.01);
		assertEquals(4.4, actual2, 0.01);

		 */
	}
}
