import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestUnionFind {

	private UnionFind union;

	@Before
	public void setUp() {
		union = new UnionFind(5);
	}

	@Test
	public void sizeOf() {
		int[] expected = {-1, -1, -1, -1, -1};
		Assert.assertArrayEquals(expected, union.parent);
		Assert.assertEquals(1, union.sizeOf(1));
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