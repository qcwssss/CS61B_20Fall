package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
	private Node root;

	private static class Node {
		Point point;
		Node left;
		Node right;
		private Orientation orient;

		Node(Point p, Orientation o) {
			point = p;
			orient = o;
		}
	}

	public KDTree(List<Point> points) {
		for (Point p : points) {
			root = put(p, root, Orientation.HORIZONTAL);
		}
	}

	@Override
	public Point nearest(double x, double y) {
		return nearest(root, new Point(x, y), root, 'x').point;
	}

	private Node nearest(Node node, Point goal, Node best, char axis) {
		if (node == null) {
			return best;
		}if (Point.distance(goal, node.point) < Point.distance(goal, best.point)) {
			best = node;
		}

		double axisDifference = axisDiff(goal, node.point, node.orient);

		Node goodSide;
		Node badSide;
		if (axisDifference <= 0) {
			goodSide = node.left;
			badSide = node.right;
		} else {
			goodSide = node.right;
			badSide = node.left;
		}

		best = nearest(goodSide, goal, best, switchAxis(axis));
		// Check to prune bad side or not.
		if (axisDifference * axisDifference < Point.distance(goal, best.point)) {
			best = nearest(badSide, goal, best, switchAxis(axis));
		}
		return best;
	}

	private Node put(Point point, Node x, Orientation oldOrient) {
		if (x == null) {
			return new Node(point, oldOrient);
		}
		/** Prevent duplicate node. */
		if (point.equals(x.point)) {
			return x;
		}

		if (axisDiff(point, x.point, x.orient) <= 0) {
			x.left = put(point, x.left, oldOrient.opposite());
		} else {
			x.right = put(point, x.right, oldOrient.opposite());
		}
		return x;
	}

	private double axisDiff(Point p1, Point p2, Orientation o) {
		if (o == Orientation.HORIZONTAL) {
			return p1.getX() - p2.getX();
		} else {
			return p1.getY() - p2.getY();
		}
	}

	private char switchAxis(char axis) {
		if (axis == 'x') {
			return 'y';
		} else {
			return 'x';
		}
	}
}
