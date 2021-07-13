package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;
    private Node root;

    private class Node {
        private Point p;
        private boolean orientation;
        private Node leftBottom;
        private Node rightTop;

        Node(Point p, boolean orientation) {
            this.p = p;
            this.orientation = orientation;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, HORIZONTAL);
        }
    }

    private Node add(Point p, Node n, boolean orientation) {
        if (n == null) {
            return new Node(p, orientation);
        }
        if (p.equals(n.p)) {
            return n;
        }

        int cmp = comparePoints(p, n.p, orientation);
        if (cmp < 0) {
            n.leftBottom = add(p, n.leftBottom, !orientation);
        } else {
            n.rightTop = add(p, n.rightTop, !orientation);
        }
        return n;
    }

    private int comparePoints(Point a, Point b, boolean orientation) {
        if (orientation == HORIZONTAL) {
            return Double.compare(a.getX(), b.getX());
        } else {
            return Double.compare(a.getY(), b.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root.p);
    }

    private Point nearest(Node n, Point target, Point best) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.p, target) < Point.distance(best, target)) {
            best = n.p;
        }
        int cmp = comparePoints(target, n.p, n.orientation);
        Node goodSide, badSide;
        if (cmp < 0) {
            goodSide = n.leftBottom;
            badSide = n.rightTop;
        } else {
            goodSide = n.rightTop;
            badSide = n.leftBottom;
        }
        best = nearest(goodSide, target, best);
        if (isWorthLooking(n, target, best)) {
            best = nearest(badSide, target, best);
        }
        return best;
    }

    private boolean isWorthLooking(Node n, Point target, Point best) {
        double distToBest = Point.distance(best, target);
        double distToBad;
        if (n.orientation == HORIZONTAL) {
            distToBad = Point.distance(new Point(target.getX(), n.p.getY()), target);
        } else {
            distToBad = Point.distance(new Point(n.p.getX(), target.getY()), target);
        }
        return distToBad < distToBest;
    }

    public static void main(String[] args) {

    }
}
