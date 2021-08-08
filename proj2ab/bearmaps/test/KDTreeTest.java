package bearmaps.test;

import bearmaps.KDTree;
import bearmaps.NaivePointSet;
import bearmaps.Point;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {
	private final Random r = new Random(500);


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

	/**
	 * Nearest test helper.
	 * @param pointsCount
	 * @param queryCount
	 */
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
	public void testRandomNearestThousand() {
		int pointsCount = 1000;
		int queryCount = 200;
		testWithNPointsAndQQueries(pointsCount, queryCount);

	}


	@Test
	public void testRandomNearestTenThousand() {
		int pointsCount = 10000;
		int queryCount = 2000;
		testWithNPointsAndQQueries(pointsCount, queryCount);

	}

	/** Timing table. */
	private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
		System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
		System.out.printf("------------------------------------------------------------\n");
		for (int i = 0; i < Ns.size(); i += 1) {
			int N = Ns.get(i);
			double time = times.get(i);
			int opCount = opCounts.get(i);
			double timePerOp = time / opCount * 1e6;
			System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
		}
	}

	/**
	 * Print KD-Tree Construction Timing table.
	 */
	public void timeKDTreeConstruction() {
		// constant
		int base = 31250;
		int exponent = 7;

		Stopwatch sw = new Stopwatch();
		// store N , time (s) numbers
		List<Integer> dataSize = new ArrayList<>();
		ArrayList<Double> time = new ArrayList<>();

		// a list of size
		for (int i = 0; i < exponent; i++) {
			dataSize.add(base * (int)Math.pow(2, i));
		}
		System.out.println("Timing table for Kd-Tree Construction");

		for (Integer num : dataSize) {
			// test KD tree
			// kdT add num points
			// store time to list
			List<Point> pList = pointsList(num);
			KDTree KDTest = new KDTree(pList);

			time.add(sw.elapsedTime());
		}

		printTimingTable(dataSize, time, dataSize);

	}

	public void timeNPSetNearest() {
		// constant
		int base = 125;
		int exponent = 4;

		Stopwatch sw = new Stopwatch();
		// store N , time (s) numbers
		List<Integer> dataSize = new ArrayList<>();
		ArrayList<Double> time = new ArrayList<>();

		// a list of size
		for (int i = 0; i < exponent; i++) {
			dataSize.add(base * (int)Math.pow(2, i));
		}
		System.out.println("\n\nTiming table for NaivePointSet Construction");

		for (Integer num : dataSize) {
			List<Point> pList = pointsList(1000000);
			NaivePointSet nps = new NaivePointSet(pList);

			time.add(sw.elapsedTime());
		}

		printTimingTable(dataSize, time, dataSize);

	}

	private void timeTableNearest(Boolean isKDTree) {
		// constant
		int base, exponent;
		if (isKDTree) {
			base = 31250;
			exponent = 7;
		} else {
			base = 125;
			exponent = 4;
		}

		Stopwatch sw = new Stopwatch();
		// store N , time (s) numbers
		List<Integer> dataSize = new ArrayList<>();
		ArrayList<Double> time = new ArrayList<>();
		List<Integer> opsList = new ArrayList<>();

		int max = 100000;
		// a list of size
		for (int i = 0; i < exponent; i++) {
			dataSize.add(base * (int)Math.pow(2, i));
			opsList.add(max);
		}

		String tableTitle;
		if (isKDTree) {
			tableTitle = "Timing table for KD-Tree Nearest";
		} else {
			tableTitle = "Timing table for NaivePointSet Nearest";
		}
		System.out.println(tableTitle);

		List<Point> queries = pointsList(max);

		for (Integer num : dataSize) {
			List<Point> pList = pointsList(num);


			if (isKDTree) {
				KDTree KD = new KDTree(pList);
				for (Point p : queries) {
					Point nrst = KD.nearest(p.getX(), p.getY());
				}
			} else {
				NaivePointSet nps = new NaivePointSet(pList);
				for (Point p : queries) {
					Point nrst = nps.nearest(p.getX(), p.getY());
				}
			}

			time.add(sw.elapsedTime());
		}

		printTimingTable(dataSize, time, opsList);

	}

	@Test
	public void testKDTreeTimingTable() {
		//timeKDTreeConstruction();
		//timeNPSetConstruction();
		timeTableNearest(true);
		timeTableNearest(false);

	}

	@Test
	public void timeTestFromChen() {
		List<Point> randomPoints = pointsList(100000);
		KDTree kd = new KDTree(randomPoints);
		NaivePointSet nps = new NaivePointSet(randomPoints);
		List<Point> queryPoints = pointsList(10000);

		long start = System.currentTimeMillis();
		/*
		for (Point point: queryPoints) {
			nps.nearest(point.getX(),point.getY());
		}
		long end = System.currentTimeMillis();
		System.out.println("After 10000 queries, NaivePointSet spends " + (end - start) +" time.");
*/
		start = System.currentTimeMillis();
		for (Point point: queryPoints) {
			kd.nearest(point.getX(),point.getY());
		}
		long end = System.currentTimeMillis();
		System.out.println("After 10000 queries, KD-Tree spends " + (end - start) +" time.");

	}
}
