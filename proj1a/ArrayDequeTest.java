import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for ArrayDeque class.
 * @author Chen Qiu
 */
public class ArrayDequeTest {
	private ArrayDeque<Integer> deque;
	private ArrayDeque<String> deque1;
	private ArrayDeque<Integer> dequeCopy;

	@Before
	public void setUp() {
		deque = new ArrayDeque<>();
		deque1 = new ArrayDeque<>();
		//dequeCopy = new ArrayDeque<>();

	}
	@Test
	public void testIsEmpty() {
		boolean actual = deque.isEmpty();
		// test size
		assertEquals(0, deque.size());

        assertEquals(true, actual);
	}

	@Test
	public void testIsFull() {
		assertEquals(false, deque.isFull());

		// fill array deque1
		deque1.addFirst("fill");
		deque1.addFirst("to");
		deque1.addFirst("going");
		deque1.addFirst("am");
		deque1.addFirst("I");

		deque1.addLast("this");
		deque1.addLast("Array");
		deque1.addLast("Deque1");

		deque1.printDeque();
		// test capacity and size
		assertEquals(8, deque1.getCapacity() );
		assertEquals(8,deque1.size());

		assertEquals(true, deque1.isFull());
	}

	@Test
	public void testGetters() {
		assertEquals(4, deque.getNextFirst());
		assertEquals(5, deque.getNextLast());
		assertEquals(8, deque.getCapacity());

	}

	@Test
	public void testAddFirst() {

		deque.addFirst(3);
		deque.addFirst(2);
		deque.addFirst(1);
		// test size
		assertEquals(3, deque.size());

		deque.addFirst(4);
		deque.addFirst(5);
		deque.addFirst(6);
		assertEquals(6, deque.getNextFirst());

		deque.addFirst(7);
		deque.addFirst(8);
		deque.addFirst(1001);// add after full

		deque.printDeque();
	}

	@Test
	public void testRemoveFirst() {
		deque.addFirst(6);
		deque.addFirst(5);
		deque.addFirst(4);
		deque.printDeque();

		int actual = deque.removeFirst();
		assertEquals(4, actual);

		int actual1 = deque.removeFirst();
		assertEquals(5, actual1);

		int actual2 = deque.removeFirst();
		assertEquals(6, actual2);

		// test cycle
		deque.addFirst(6);
		deque.addFirst(5);
		deque.addFirst(4);
		deque.addFirst(3);
		deque.addFirst(2);
		deque.addFirst(1);
		int actual3 = deque.removeFirst();
		assertEquals(1, actual3);
		int actual4 = deque.removeFirst();
		assertEquals(2, actual4);
	}

	@Test
	public void testRemoveLast() {
		deque1.addLast("a");
		deque1.addLast("b");
		deque1.addLast("c");
		// test
		String actual = deque1.removeLast();
		assertEquals("c", actual);

		String actual1 = deque1.removeLast();
		assertEquals("b", actual1);

		String actual2 = deque1.removeLast();
		assertEquals("a", actual2);

		// test circulate
		deque1.addLast("a");
		deque1.addLast("b");
		deque1.addLast("c");
		deque1.addLast("head");
		assertEquals(1,deque1.getNextLast());
		assertEquals("head", deque1.removeLast());
		assertEquals("c", deque1.removeLast());

		deque1.printDeque();
	}
	/**
	 * This test passed, set circulateNext private.
	@Test
	public void testCirculateNext() {
		int input = deque1.getCapacity() - 1;
		int actual = deque1.circulateNext(input);

		int input1 = 0;
		int actual1 = deque.circulateNext(input1);

		int input2 = 3;
		int actual2 = deque.circulateNext(input2);

		assertEquals(0, actual);
		assertEquals(7, actual1);
		assertEquals(3, actual2);
	}
	 */


	@Test
	public void testGet() {
		deque1.addLast("head");
		deque1.addLast(" * ");
		deque1.addLast(" ** ");
		deque1.addLast("tail");
		deque1.printDeque();

		String expected = "head";
		String actual = deque1.get(deque1.getNextLast()-1);

		assertEquals(expected, deque1.get(5));
		assertEquals(" * ", deque1.get(6));
		assertEquals(" ** ", deque1.get(7));
		assertEquals("tail", actual);

		// test the index of nextLast
		assertEquals(1, deque1.getNextLast());
		// add item till out of bound
		deque1.addLast("Whoops!");
		deque1.addLast("more");
		deque1.addLast("and");
		deque1.addLast("more!");

		deque1.addLast("It should explode now!");
	}

	@Test
	public void testDeepCopy() {
		deque.addFirst(3);
		deque.addFirst(2);
		deque.addFirst(1);

		dequeCopy = new ArrayDeque(deque);
		// compare two objects attributes.
		assertArrayEquals(deque.getItems(), dequeCopy.getItems());
		assertEquals(dequeCopy.size(), deque.size());
		assertEquals(dequeCopy.getCapacity(), deque.getCapacity());
		assertEquals(dequeCopy.getNextFirst(), deque.getNextFirst());
		assertEquals(dequeCopy.getNextLast(), deque.getNextLast());

	}

	@Test
	public void testResize() {
		// test cycle
		deque.addFirst(6);
		deque.addFirst(5);
		deque.addFirst(4);
		deque.addFirst(3);
		deque.addFirst(2);
		deque.addFirst(1);
		deque.addFirst(8);
		deque.addFirst(7);

		deque.printDeque();
		// after resize
		deque.addFirst(10);
		deque.printDeque();
		int actual = deque.get(15);
		assertEquals(10, actual);

	}

	@Test
	public void testCheckSizeUsage() {
		int num = deque.getCapacity() * 2;
		for (int i = 0; i < num * 10 ; i++) {
			deque.addLast(i);
		}
		System.out.printf("Current capacity: %d; Current size: %d\n",
				deque.getCapacity(), deque.size());

		for (int i = 0; i < num * 8 ; i++) {
			deque.removeLast();
		}
		System.out.printf("Current capacity: %d; Current size: %d\n",
				deque.getCapacity(), deque.size());
		//deque.printDeque();
		assertEquals(128, deque.getCapacity());

	}

	@Test
	public void testCheckSizeUsage2() {
		int num = deque.getCapacity() * 2;
		for (int i = 0; i < num * 10 ; i++) {
			deque.addFirst(i);
		}
		System.out.printf("Current capacity: %d; Current size: %d\n",
				deque.getCapacity(), deque.size());

		for (int i = 0; i < num * 8 ; i++) {
			deque.removeFirst();
		}
		System.out.printf("Current capacity: %d; Current size: %d\n",
				deque.getCapacity(), deque.size());
		//deque.printDeque();
		assertEquals(128, deque.getCapacity());

	}


}
