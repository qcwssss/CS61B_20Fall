package bearmaps.test;

import bearmaps.ArrayHeapMinPQ;
import org.junit.Before;
import org.junit.Test;

import static bearmaps.PrintHeapDemo.printFancyHeapDrawing;
import static bearmaps.PrintHeapDemo.printSimpleHeapDrawing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
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
		
	}
}

