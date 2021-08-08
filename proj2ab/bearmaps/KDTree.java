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


		private Node(Point point, Orientation to) {
			this.p = point;
			this.orient = to;
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


	private int comparePoint(Point a, Point b, Orientation orient) {
		if (orient == Orientation.HORIZONTAL) {
			return Double.compare(a.getX(), b.getX());
		} else {
			return Double.compare(a.getY(), b.getY());
		}
	}


	// This should take \(O(\log N)\) time on average, where \(N\) is the number of points.

  /**
   * Returns the closest point to the inputted coordinates.
   * @param x
   * @param y
   * @return
   */
  public Point nearest(double x, double y) {

		//Node nn = nearestBrute(root, new Point(x, y), root);
		Node nn = nearestFast(root, new Point(x, y), root);

		return nn.p;
	}

	/** stage 2: implement efficient find nearest. */
	private Point nearestFast(Node n, Point target, Point best) {
		if (n == null) {
			return best;
		}
		// compare current node point with best
		if (distance(n.p, target) < distance(best, target)) {
				best = n.p; // update best
		}

		// recursive
		Node goodSide, badSide;
		if (comparePoint(n.p, target, n.orient) < 0) {
			// n.p < target, left child is good
			goodSide = n.left;
			badSide = n.right;
		} else {
			goodSide = n.right;
			badSide = n.left;
		}
		// consider the correct child first!
		best = nearestFast(goodSide, target, best);
		// if bad side could have sth useful
		if (isWorthLook(n, target, best)) {
			best = nearestFast(badSide, target, best);
		}

		return best;
	}

	/** A helper method for pruning.*/
	private boolean isWorthLook(Node n, Point target, Point best) {
		double curBest = Point.distance(best, target);
		// if best dist possible < curBest: -> the line separates bad&good side
		if (n.orient == Orientation.HORIZONTAL) { // compare Y
			// goal.y - d.y)^2.
			return Math.abs(n.p.getY() - target.getY()) < Math.sqrt(curBest);
		} else {
			return Math.abs(n.p.getX() - target.getX()) < Math.sqrt(curBest);
		}
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
