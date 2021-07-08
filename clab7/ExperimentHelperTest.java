import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class ExperimentHelperTest {

	@Test
	public void testOptimalIPL() {
		// *  N = 1, OIPL: 0
		// *  N = 2, OIPL: 1
		// *  N = 3, OIPL: 2
		// *  N = 4, OIPL: 4
		//     *  N = 5, OIPL: 6
		//     *  N = 6, OIPL: 8
		//     *  N = 7, OIPL: 10
		//     *  N = 8, OIPL: 13
		Assert.assertEquals(0, ExperimentHelper.optimalIPL(1));
		//int actual1 = ExperimentHelper.optimalIPL(1);
		Assert.assertEquals(1, ExperimentHelper.optimalIPL(2));
		Assert.assertEquals(2, ExperimentHelper.optimalIPL(3));
		Assert.assertEquals(4, ExperimentHelper.optimalIPL(4));

	}

	@Test
	public void optimalAverageDepth() {
	}
}