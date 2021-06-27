import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestBubbleGrid {

	@Before
	public void setUp() {
	}

	@Test
	public void testConstructors() {
		int[][] sqr3 = new int[3][3];
		BubbleGrid g1 = new BubbleGrid(sqr3);
		BubbleGrid g1b = new BubbleGrid(3, 3);
		boolean what = g1.equals(g1b);
		g1.getGrid()[1][1] = 1;
		g1b.getGrid()[1][1] = 1;

		assertTrue(g1.equals(g1b));

	}
}