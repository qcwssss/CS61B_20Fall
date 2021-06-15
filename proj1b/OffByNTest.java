import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OffByNTest {
	private CharacterComparator offByThree;
	private OffByN offByFour;
	private OffByN offBySix;


	@Before
	public void setUp() {
		offByThree = new OffByN(3);
		offByFour = new OffByN(4);
		offBySix = new OffByN(6);

	}

	@Test
	public void testEqualChars() {
		assertTrue(offByThree.equalChars('a', 'd'));
		assertTrue(offByThree.equalChars('d', 'a'));
		assertTrue(offByThree.equalChars('c', 'f'));
		assertTrue(offByThree.equalChars('b', 'e'));

		assertFalse(offByThree.equalChars('b', 'b'));
		assertFalse(offByThree.equalChars('b', 'a'));
		assertFalse(offByThree.equalChars('z', 'b'));

		assertTrue(offByFour.equalChars('a', 'e'));
		assertTrue(offByFour.equalChars('e', 'a'));
		assertFalse(offByFour.equalChars('e', 'd'));
		assertFalse(offByFour.equalChars('e', 'e'));

		assertTrue(offBySix.equalChars('a', 'g'));
		assertTrue(offBySix.equalChars('b', 'h'));
		assertFalse(offBySix.equalChars('a', 'h'));

	}
}