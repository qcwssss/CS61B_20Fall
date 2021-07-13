package hw2.Test;

import hw2.Percolation;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TestPercolation {
	Percolation p1;

	@Before
	public void setUp() {
		p1 = new Percolation(4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidConstruct() {
		Percolation pInval = new Percolation(0);
		Percolation pInval1 = new Percolation(-2);

	}

	//test once a time
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvalidOpen1() {
		p1.open(4,4);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvalidOpen2() {
		p1.open(-1,3);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testInvalidOpen3() {
		p1.open(3,-1);
	}

	@Test
	public void testOpen() {
		p1.open(0,2);
		p1.open(0,1);
		// row 0 can't be opened since full

		p1.open(2,2);
		p1.open(2,1);
		p1.open(3,3); // valid
		//p1.open(3,-1); // invalid


		assertEquals(0, p1.getStatus(3, 3));

		assertEquals(0, p1.getStatus(2, 1));
		assertEquals(0, p1.getStatus(2, 2));
		// full sites
		assertEquals(0, p1.getStatus(0, 2));
		assertEquals(0, p1.getStatus(0, 1));
	}

	@Test
	public void testIsOpen() {
		p1.open(0,0);
		p1.open(0,2);
		p1.open(0,1);
		p1.open(2,2);
		p1.open(2,1);
		p1.open(3,3); // valid
		assertTrue(p1.isOpen(0,0));
		assertTrue(p1.isOpen(0,2));
		assertTrue(p1.isOpen(0,1));
		assertTrue(p1.isOpen(2,2));
		assertTrue(p1.isOpen(2,1));
		assertTrue(p1.isOpen(3,3));

		assertFalse(p1.isOpen(3,2));
		assertFalse(p1.isOpen(0,3));
		assertFalse(p1.isOpen(2,0));

	}

	@Test
	public void testIsFull() {
		p1.open(0,0);
		assertTrue(p1.isFull(0, 0));
	}

	@Test
	public void testNumberOfOpen() {
		assertEquals(0, p1.numberOfOpenSites());
		p1.open(2,2);
		p1.open(2,1);
		assertEquals(2, p1.numberOfOpenSites());

		p1.open(3,3);
		p1.open(3,0);
		assertEquals(4, p1.numberOfOpenSites());
	}

	@Test
	public void testXYTo1D() {
		assertEquals(5, p1.xyTo1D(1,1));
		assertEquals(13, p1.xyTo1D(3,1));
		assertEquals(14, p1.xyTo1D(3,2));
		assertEquals(15, p1.xyTo1D(3,3));

	}

	@Test
	public void testConnectOpen() {
		p1.open(1,1);
		p1.open(2,1);
		boolean actual5_10 = p1.isConnected(p1.xyTo1D(1,1), p1.xyTo1D(2,1) );
		assertTrue(actual5_10);
		p1.open(3,2);
		p1.open(3,3);
		assertTrue(p1.isConnected(p1.xyTo1D(3,2), p1.xyTo1D(3,3)) );

		// connect all open sites
		p1.open(3, 1);
		assertTrue(p1.isConnected(5, 15)); // (1,1) and (3,3)
		assertTrue(p1.isConnected(p1.xyTo1D(1,1), p1.xyTo1D(3,3)));
		assertTrue(p1.isConnected(p1.xyTo1D(1,1), p1.xyTo1D(3,2)));
		assertTrue(p1.isConnected(p1.xyTo1D(1,1), p1.xyTo1D(3,1)));

		assertTrue(p1.isConnected(p1.xyTo1D(2,1), p1.xyTo1D(3,3)));
		assertTrue(p1.isConnected(p1.xyTo1D(2,1), p1.xyTo1D(3,2)));
		assertTrue(p1.isConnected(p1.xyTo1D(2,1), p1.xyTo1D(3,1)));

	}


}