package bearmaps;

import org.junit.platform.engine.support.hierarchical.Node;

import java.util.Comparator;
import java.util.List;

import static bearmaps.Point.distance;
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
			if (root == null) { // create root
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

		/** Prevent duplicate node. */
		if (point.equals(x.p)) {
			return x;
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

	// stage 1: brute force
	public Point nearestBrute(double x, double y) {
		Point goal = new Point(x, y);
		double curDistSqr = distance(goal, root.p); // ^2
		return null;
	}

	private Double nearestBrute(Node x, Point target, double dMin) {
		dMin = Math.min(distance(target, x.p), dMin);
		if (x.right != null || x.left != null) {
			if (x.left != null) {
				dMin = nearestBrute(x.left, target, dMin);
			}
			if (x.right != null) {
				dMin = nearestBrute(x.right, target, dMin);
			}
		}
		return dMin;

	}

	/** KDTree test method. */
	public static void buildLectureTree() {
		Point a = new Point(2, 3);
		Point b = new Point(4, 2);
		Point c = new Point(4, 5);
		Point d = new Point(3, 3);
		Point e = new Point(1, 5);
		Point f = new Point(4, 4);

		KDTree kd = new KDTree(List.of(a, b, c, d, e, f, a));
	}

	public static void buildDuplicateNode() {
		Point a = new Point(2, 3);
		Point b = new Point(4, 2);
		Point c = new Point(2, 3);

		KDTree kd = new KDTree(List.of(a, b, c));
		//KDTree kd = new KDTree(List.of(a, c));

	}


	public static void main(String[] args) {
		//buildDuplicateNode();
		buildLectureTree();


	}
}
