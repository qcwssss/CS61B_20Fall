package bearmaps.test;

import bearmaps.ArrayHeapMinPQ;
import bearmaps.NaiveMinPQ;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static bearmaps.PrintHeapDemo.printFancyHeapDrawing;
import static bearmaps.PrintHeapDemo.printSimpleHeapDrawing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
	private final Random r = new Random(500);
	private ArrayHeapMinPQ<Character> Aheap;

	@Before
	public void setUp() {
		Aheap = new ArrayHeapMinPQ<>();
		Aheap.add('a', 1);
		Aheap.add('b', 3);
		Aheap.add('c', 2);
		Aheap.add('d', 5);
		Aheap.add('e', 4);
		Aheap.add('z', -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddException() {
		Aheap.add('z', 15);

	}

	@Test
	public void testAddSize() {
		assertEquals(6, Aheap.size());
		Aheap.add('n', 10);
		Aheap.add('m', 20);
		assertEquals(8, Aheap.size());

		boolean expected1 = Aheap.contains('m');
		assertTrue(expected1);
		assertTrue(Aheap.contains('a'));
		assertTrue(Aheap.contains('b'));

		Object[] p3 = new Object[Aheap.size() + 1];
		for (int i = 1; i < Aheap.size() + 1; i++ ) {
			p3[i] = Aheap.removeSmallest();
		}

		//printSimpleHeapDrawing(print2);


	}

	@Test
	public void testContains() {
		int num = 10000;
		ArrayHeapMinPQ<Integer> in1 = new ArrayHeapMinPQ<>();
		for (int i = 0 ; i < num; i++) {
			in1.add(i,2*i);
			assertTrue(in1.contains(i));
		}

		int actual = in1.getSmallest();
		assertEquals(0, actual);

	}

	@Test
	public void testGetSmallest() {
		char actual1 = Aheap.getSmallest();
		assertEquals('z', actual1);

		int num = 20000;
		int actual2;
		ArrayHeapMinPQ<Integer> in1 = new ArrayHeapMinPQ<>();
		for (int i = num ; i > 0; i--) {
			in1.add(i,2*i);

			actual2 = in1.getSmallest();
			assertEquals(i, actual2);

		}
	}

	/** Random test, taking advantage of NaiveMInPQ. */
	@Test
	public void testGetSmallestRandomly() {
		int num = 10000;

		ArrayHeapMinPQ<Integer> ahPQ = new ArrayHeapMinPQ<>();
		NaiveMinPQ<Integer> npq = new NaiveMinPQ<>();

		for (int i = 0; i < num; i++) {
			int intR = r.nextInt();
			double wR = r.nextDouble();
			ahPQ.add(intR, wR);
			npq.add(intR, wR);
			assertEquals(npq.getSmallest(), ahPQ.getSmallest());
			assertEquals(npq.contains(intR), ahPQ.contains(intR));
		}

	}

}

