import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class testSort {

	@Test
	public void testFindSmallest() {
		String [] input = {"i", "have", "a", "pen"};
		int expected = 2;
		int actual = Sort.findSmallest(input, 0);
		assertEquals(expected, actual);

		String[] input1 = {"there", "are", "many", "pigs"};
		int expected1 = 1;
		int actual1 = Sort.findSmallest(input1, 1);
		org.junit.Assert.assertEquals(expected1, actual1);

		String [] input2 = {"those", "are", "fat", "pigs"};
		int expected2 = 2;
		int actual2 = Sort.findSmallest(input2, 2);
		assertEquals(expected2, actual2);
	}

	@Test
	public void testSwap() {
		// test 1
		String [] input = {"i", "have", "a", "pen"};
		String[] expected = {"a", "have", "i", "pen"};
		Sort.swap(input, 0, 2);
		assertArrayEquals(expected, input);

		//test 2
		String[] input2 = {"those", "are", "giant", "rocks"};
		String[] expected2 = {"those", "are", "rocks", "giant"};
		Sort.swap(input2, 2, 3);
		assertArrayEquals(expected2, input2);

	}

	@Test
	public void testSort() {
		String[] input = {"those", "are", "giant", "rocks"};
		String[] expected = { "are", "giant", "rocks", "those",};
		Sort.sort(input);
		assertArrayEquals(expected, input);

	}
}
