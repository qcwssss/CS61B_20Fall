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
		p1.open(2,2);
		p1.open(2,1);
		p1.open(3,3); // valid
		//p1.open(3,-1); // invalid


		assertEquals(0, p1.getStatus(3, 3));

		assertEquals(0, p1.getStatus(2, 1));
		assertEquals(0, p1.getStatus(2, 2));
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


	}
}