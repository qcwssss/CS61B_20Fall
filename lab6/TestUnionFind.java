import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class TestUnionFind {

	private UnionFind union;

	@Before
	public void setUp() {
		union = new UnionFind(15);
		// connect
		union.connect(1, 4);
		union.connect(4, 9);
		union.connect(2, 7);
		union.connect(2, 9);

		union.connect(5, 10);

		union.connect(6, 8);
		union.connect(3, 6);

	}

	@Test
	public void sizeOf() {
		UnionFind u1 = new UnionFind(5);
		int[] expected = {-1, -1, -1, -1, -1};
		assertArrayEquals(expected, u1.parent);
		assertEquals(1, u1.sizeOf(1));

		assertEquals(3, union.sizeOf(3));
		assertEquals(3, union.sizeOf(6));
		assertEquals(3, union.sizeOf(8));

		assertEquals(2, union.sizeOf(5));
		assertEquals(2, union.sizeOf(5));

		assertEquals(5, union.sizeOf(1));
		assertEquals(5, union.sizeOf(4));
		assertEquals(5, union.sizeOf(9));
		assertEquals(5, union.sizeOf(7));
		assertEquals(5, union.sizeOf(2));

	}

	@Test
	public void testParent() {
		assertEquals(4, union.parent(1));
		assertEquals(-5, union.parent(4));
		assertEquals(4, union.parent(7));
		assertEquals(4, union.parent(9));
		assertEquals(7, union.parent(2));

		assertEquals(8, union.parent(3));
		assertEquals(8, union.parent(6));
		assertEquals(-3, union.parent(8));

		assertEquals(10, union.parent(5));
		assertEquals(-2, union.parent(10));

	}

	@Test
	public void isConnected() {
		assertTrue(union.isConnected(1, 4));
		assertTrue(union.isConnected(1, 7));
		assertTrue(union.isConnected(1, 9));
		assertTrue(union.isConnected(1, 2));

		assertTrue(union.isConnected(2, 7));

		assertTrue(union.isConnected(7, 9));
		assertTrue(union.isConnected(2, 9));
		assertTrue(union.isConnected(4, 9));

	}

	@Test
	public void testFind() {
		assertEquals(4, union.find(1));
		assertEquals(4, union.find(9));
		assertEquals(4, union.find(7));
		assertEquals(4, union.find(2));
		assertEquals(4, union.find(4));

		assertEquals(8, union.find(3));
		assertEquals(8, union.find(6));
		assertEquals(8, union.find(8));

		assertEquals(10, union.find(5));
		assertEquals(10, union.find(10));

	}
}