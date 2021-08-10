package bearmaps.test;

import bearmaps.ArrayHeapMinPQ;
import org.junit.Before;
import org.junit.Test;

import static bearmaps.PrintHeapDemo.printFancyHeapDrawing;
import static bearmaps.PrintHeapDemo.printSimpleHeapDrawing;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
	private ArrayHeapMinPQ<Character> Aheap;

	@Before
	public void setUp() {
		Aheap = new ArrayHeapMinPQ<>();
	}

	@Test
	public void testAddSize() {
		Aheap.add('a', 1);
		Aheap.add('b', 3);
		Aheap.add('c', 2);
		Aheap.add('d', 5);
		Aheap.add('e', 4);
		Aheap.add('z', -1);

		assertEquals(6, Aheap.size());

		Object[] print1 = {1, 1, 3, 2, 5, 4, -1, 7};
		Object[] print2 = {0, 7, 8, 2, 5, 4, 1, 7};
		Object[] p3 = new Object[Aheap.size() + 1];
		for (int i = 1; i < Aheap.size() + 1; i++ ) {
			p3[i] = Aheap.removeSmallest();
		}

		printSimpleHeapDrawing(print1);
		printSimpleHeapDrawing(print2);


	}
}

