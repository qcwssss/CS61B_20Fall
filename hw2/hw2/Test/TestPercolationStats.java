package hw2.Test;

import hw2.PercolationFactory;
import hw2.PercolationStats;

import org.junit.Before;
import org.junit.Test;

public class TestPercolationStats {
	private PercolationStats stats;

	@Before
	public void setUp() {
		PercolationFactory pf = new PercolationFactory();
		stats = new PercolationStats(3, 30, pf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalConstruct() {
		PercolationFactory pf = new PercolationFactory();
		PercolationStats illegal = new PercolationStats(-1 , 1 ,pf);
	}

	@Test
	public void mean() {
	}

	@Test
	public void stddev() {
	}

	@Test
	public void confidenceLow() {
	}

	@Test
	public void confidenceHigh() {
	}
}