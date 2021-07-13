package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> lst;

    public NaivePointSet(List<Point> points) {
        lst = new ArrayList<>();
        lst.addAll(points);
    }

    @Override
    public Point nearest(double x, double y) {
        Point best = lst.get(0);
        Point target = new Point(x, y);
        double bestDistance = Point.distance(best, target);
        for (Point p : lst) {
            double currentDistance = Point.distance(p, target);
            if (currentDistance < bestDistance) {
                best = p;
                bestDistance = currentDistance;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
