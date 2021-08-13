package bearmaps.test;

import bearmaps.ArrayHeapMinPQ;
import bearmaps.NaiveMinPQ;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static bearmaps.PrintHeapDemo.printFancyHeapDrawing;
import static org.junit.Assert.*;

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

		/*Print heap */
		/*
		Object[] p3 = new Object[Aheap.size() + 1];
		for (int i = 1; i < Aheap.size() + 1; i++ ) {
			p3[i] = Aheap.removeSmallest();
		}

		 */

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


	@Test
	public void testRemoveSmallestAndContains() {
		int num = 5000;
		Object[] inArr = new Object[num + 1];
		inArr[0] = 0;
		ArrayHeapMinPQ<Integer> a1 = new ArrayHeapMinPQ<>();
		for (int i = 1; i< num; i++) {
			inArr[i] = i;
			a1.add(i, i);
		}
		printFancyHeapDrawing(inArr);
		for (int i = 1; i < num; i++) {
			assertEquals(i, (int) a1.removeSmallest());
			assertFalse(a1.contains(i));

		}

		//Object[] arr2 = { 1, 2, 3, 4, 5, 6, 7};
		//printFancyHeapDrawing(arr2);

	}

	@Test
	public void testChangePriority() {
		int num = 10000;
		Object[] inArr = new Object[num + 1];
		inArr[0] = 0;
		ArrayHeapMinPQ<Integer> a1 = new ArrayHeapMinPQ<>();
		for (int i = 1; i< num; i++) {
			inArr[i] = i;
			a1.add(i, i);
		}
		//printFancyHeapDrawing(inArr);

		for (int i = 1; i < num; i++) {
			a1.changePriority(i, num + i -1 );
			assertTrue(a1.contains(i));
			if (i == num - 1) {
				assertEquals(1, (int) a1.getSmallest());
			} else {
				assertEquals(i + 1, (int) a1.getSmallest());
			}
		}


	}


	/** Random test, taking advantage of NaiveMInPQ. */
	@Test
	public void testSmallestAndContainsRandomly() {
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

	@Test
	public void testRemoveSmallestRandomly() {
		int num = 50000;

		ArrayHeapMinPQ<Integer> ahPQ = new ArrayHeapMinPQ<>();
		NaiveMinPQ<Integer> npq = new NaiveMinPQ<>();

		for (int i = 0; i < num; i++) {
			int intR = r.nextInt();
			double wR = r.nextDouble();
			ahPQ.add(intR, wR);
			npq.add(intR, wR);
			if (i > num/2) {
				assertEquals(npq.removeSmallest(), ahPQ.removeSmallest());
			}
		}
	}

    @Test
    public void testChangePriorityRandomly() {
		int num = 10000;

		ArrayHeapMinPQ<Integer> ahPQ = new ArrayHeapMinPQ<>();
		NaiveMinPQ<Integer> npq = new NaiveMinPQ<>();

		for (int i = 0; i < num; i++) {
			int intR = i + 1;
			double wR = r.nextDouble();
			ahPQ.add(intR, wR);
			npq.add(intR, wR);
			if (i > num / 2) {
			ahPQ.changePriority(intR / 2, wR / 2);
			npq.changePriority(intR / 2, wR / 2);

			assertEquals(npq.getSmallest(), ahPQ.getSmallest());
			}
		}
	}

	private void testRunningTime(int num) {
		num = num > 1000 ? num : 1000;

		ArrayHeapMinPQ<Integer> ahPQ = new ArrayHeapMinPQ<>();
		NaiveMinPQ<Integer> npq = new NaiveMinPQ<>();

		for (int i = 0; i < num; i++) {
			int intR = r.nextInt();
			double wR = r.nextDouble();
			ahPQ.add(intR, wR);
			npq.add(intR, wR);
		}


		long getStartAH = System.currentTimeMillis();
		for (int i = 0; i < num/100; i++) {
			ahPQ.getSmallest();
		}
		long getEndAH = System.currentTimeMillis();
		System.out.println("Total Time elapsed of ArrayHeapMinPQ getSmallest(): "
				+ (getEndAH - getStartAH)/1000.0  + "seconds.");

		long getStartNaive = System.currentTimeMillis();
		for (int i = 0; i < num/100; i++) {
			npq.getSmallest();
		}
		long getEndNaive = System.currentTimeMillis();
		System.out.println("Total Time elapsed of NaiveMinPQ getSmallest(): "
				+ (getEndNaive - getStartNaive)/1000.0  + "seconds.");


		long removeStartAH = System.currentTimeMillis();
		for (int i = 0; i < num/100; i++) {
			ahPQ.removeSmallest();
		}
		long removeEndAH = System.currentTimeMillis();
		System.out.println("Total Time elapsed of ArrayHeapMinPQ getSmallest(): "
				+ (removeEndAH - removeStartAH)/1000.0  + " seconds.");

		long removeStartNaive = System.currentTimeMillis();
		for (int i = 0; i < num/100; i++) {
			npq.removeSmallest();
		}
		long removeEndNaive = System.currentTimeMillis();
		System.out.println("Total Time elapsed of NaiveMinPQ getSmallest(): "
				+ (removeEndNaive - removeStartNaive)/1000.0  + " seconds.");

	}
	

	@Test
	public void compareRunnigTime() {
		testRunningTime(100000);
	}

}

