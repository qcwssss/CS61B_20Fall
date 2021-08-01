package bearmaps;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

	@Test
	public void testKDTree() {
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
