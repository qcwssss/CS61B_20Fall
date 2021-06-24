package creatures;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestClorus {
	private Clorus c1;
	private Clorus c2;

	@Before
	public void setUp() {
		c1 = new Clorus();
		c2 = new Clorus(2.5);
	}

	@Test
	public void testClorusBasics() {
		assertEquals(2.5, c2.energy(), 0.01);
		assertEquals(new Color(34,0,231),c2.color());
		c2.move();
		assertEquals(2.47, c2.energy(), 0.001);
		for(int i = 0; i < 50; i++) {
			c1.move();
		}
	}



}