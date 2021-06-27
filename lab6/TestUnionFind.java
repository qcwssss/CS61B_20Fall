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

		//setUp();
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

	void parent() {
	}

	void isConnected() {
	}

	void connect() {
	}

	void find() {
	}
}