package bearmaps.test;

import bearmaps.KDTree;
import bearmaps.NaivePointSet;
import bearmaps.Point;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {
	private final Random r = new Random(500);

	@Test
	public void testKDTree() {

	}

	@Test
	public void testSimpleKDT() {
		Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
		Point p2 = new Point(3.3, 4.4);
		Point p3 = new Point(-2.9, 4.2);
		// NaivePointSet
		NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
		Point ret = nn.nearest(3.0, 4.0); // returns p2

		assertEquals(p2, ret);

		// KDTree
		KDTree kd = new KDTree(List.of(p1, p2, p3));
		Point npKD = kd.nearest(3.0, 4.0);
		assertTrue(npKD.equals(p2));

	}

	@Test
	public void testNearestDemoSlide() {
		Point a = new Point(2, 3);
		Point b = new Point(4, 2);
		Point c = new Point(4, 5);
		Point d = new Point(3, 3);
		Point e = new Point(1, 5);
		Point f = new Point(4, 4);

		KDTree kd = new KDTree(List.of(a, b, c, d, e, f, a));
		Point nnE = kd.nearest(1,2); // nearest to e
		Point nnE2 = kd.nearest(0,7); // nearest to e

		assertEquals(a , nnE);
		assertEquals(e , nnE2);

	}

	private Point randomPoint() {
		double x = r.nextDouble();
		double y = r.nextDouble();
		return new Point(x, y);
	}

	private List<Point> pointsList(int N) {
		List<Point> listOfPoints = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			listOfPoints.add(randomPoint());
		}
		return listOfPoints;
	}

	private void testWithNPointsAndQQueries(int pointsCount, int queryCount) {
		List<Point> points1000 = pointsList(pointsCount);
		List<Point> query200 = pointsList(queryCount);

		NaivePointSet naiveST = new NaivePointSet(points1000);
		KDTree kdTree = new KDTree(points1000);

		for (Point p : query200) {
			Point expected = naiveST.nearest(p.getX(), p.getY());
			Point actual = kdTree.nearest(p.getX(), p.getY());
			assertEquals(expected, actual);
		}
	}

	@Test
	public void testRandomNearest() {
		int pointsCount = 1000;
		int queryCount = 200;
		testWithNPointsAndQQueries(pointsCount, queryCount);

	}
}
