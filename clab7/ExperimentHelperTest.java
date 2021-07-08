import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ExperimentHelperTest {

	@Test
	public void testOptimalIPL() {

		assertEquals(0, ExperimentHelper.optimalIPL(1));
		//int actual1 = ExperimentHelper.optimalIPL(1);
		assertEquals(1, ExperimentHelper.optimalIPL(2));
		assertEquals(2, ExperimentHelper.optimalIPL(3));
		assertEquals(4, ExperimentHelper.optimalIPL(4));
		assertEquals(6, ExperimentHelper.optimalIPL(5));
		assertEquals(8, ExperimentHelper.optimalIPL(6));
		int actual8 = ExperimentHelper.optimalIPL(8);
		assertEquals(13, actual8);

	}

	@Test
	public void testOptimalAverageDepth() {
		// N = 1, OAD: 0
		assertEquals(0, ExperimentHelper.optimalAverageDepth(1), 0.001);
		// N = 5, OAD: 1.2
		assertEquals(1.2, ExperimentHelper.optimalAverageDepth(5), 0.001);
		// N = 8, OAD: 1.625
		assertEquals(1.625, ExperimentHelper.optimalAverageDepth(8), 0.001);

	}
}