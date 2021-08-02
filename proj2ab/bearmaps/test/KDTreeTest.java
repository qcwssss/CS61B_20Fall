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
		double actual1 = ret.getX(); // evaluates to 3.3
		double actual2 = ret.getY(); // evaluates to 4.4

		assertEquals(p2, ret);
		assertEquals(3.3, actual1, 0.01);
		assertEquals(4.4, actual2, 0.01);
		// KDTree
		KDTree kd = new KDTree(List.of(p1, p2, p3));
		Point npKD = kd.nearest(3.0, 4.0);
		assertTrue(npKD.equals(p2));

	}

	@Test
	public void TestRandomNearest() {
		Random random = new Random();
		int max = 2000;
		LinkedList<Point> randPoints = new LinkedList<>();
		for (int i = 0; i < max; i++) {
			Point p = new Point(random.nextInt(), random.nextInt() );
			randPoints.add(p);
		}

		Point first = randPoints.getFirst();
		Point third = randPoints.get(2);

		NaivePointSet naiveST = new NaivePointSet(randPoints);
		KDTree kdTree = new KDTree(randPoints);

		Point nSet1 = naiveST.nearest(first.getX(), first.getY());
		Point nKD1 = kdTree.nearest(first.getX(), first.getY());

		Point nSet3 = naiveST.nearest(third.getX(), third.getY());
		Point nKD3 = kdTree.nearest(third.getX(), third.getY());

		assertEquals(nSet1.toString(), nKD1.toString());
		assertEquals(nSet3.toString(), nKD3.toString());

	}
}
