package byow.Core.Map.World;

import byow.Core.Map.Position;

import java.util.List;

public class KDTree {
	private Node root;

	private static class Node {
		Position point;
		Node left;
		Node right;
		private Orientation orient;

		Node(Position p, Orientation o) {
			point = p;
			orient = o;
		}
	}

	public KDTree(List<Position> points) {
		for (Position p : points) {
			root = put(p, root, Orientation.HORIZONTAL);
		}
	}

	public Position nearest(int x, int y) {
		return nearest(root, new Position(x, y), root.point);
	}

	private Position nearest(Node node, Position goal, Position best) {
		if (node == null) {
			return best;
		}if (Position.distance(goal, node.point) < Position.distance(goal, best)) {
			best = node.point;
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

		best = nearest(goodSide, goal, best);
		// Check to prune bad side or not.
		if (axisDifference * axisDifference < Position.distance(goal, best)) {
			best = nearest(badSide, goal, best);
		}
		return best;
	}

	private Node put(Position point, Node x, Orientation oldOrient) {
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

	private double axisDiff(Position p1, Position p2, Orientation o) {
		if (o == Orientation.HORIZONTAL) {
			return p1.getX() - p2.getX();
		} else {
			return p1.getY() - p2.getY();
		}
	}


}
