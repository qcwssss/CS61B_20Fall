import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for ArrayDeque class.
 *
 * @author Chen Qiu
 */
public class ArrayDequeTest {
	private ArrayDeque<Integer> deque;
	private ArrayDeque<String> deque1;

	@Before
	public void setUp() {
		deque = new ArrayDeque<>();
		deque1 = new ArrayDeque<>();

	}
	@Test
	public void testIsEmpty() {
		boolean expected = true;
		boolean actual = deque.isEmpty();
		// test size
		Assert.assertEquals(0, deque.size());

        Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetNextFirst() {
		Assert.assertEquals(5, deque.getNextFirst());
	}


	@Test
	public void testAddFirst() {

		deque.addFirst(3);
		deque.addFirst(2);
		deque.addFirst(1);
		Assert.assertEquals(2, deque.getNextFirst());
		// test size
		Assert.assertEquals(3, deque.size());

		deque.printDeque();
	}

	@Test
	public void testRemoveFirst() {
		deque.addFirst(6);
		deque.addFirst(5);
		deque.addFirst(4);
		deque.printDeque();

		int actual = deque.removeFirst();
		Assert.assertEquals(4, actual);

		int actual1 = deque.removeFirst();
		Assert.assertEquals(5, actual1);

		int actual2 = deque.removeFirst();
		Assert.assertEquals(6, actual2);
	}

	@Test
	public void testRemoveLast() {
		deque1.addFirst("c");
		deque1.addFirst("b");
		deque1.addFirst("a");
		deque1.printDeque();
		// test
		String actual = deque1.removeLast();
		Assert.assertEquals("c", actual);

		String actual1 = deque1.removeLast();
		Assert.assertEquals("b", actual1);

		String actual2 = deque1.removeLast();
		Assert.assertEquals("a", actual2);

	}


}
