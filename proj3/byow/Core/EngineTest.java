package byow.Core;


import org.junit.Before;
import org.junit.Test;

public class EngineTest {
	private Engine engine;

	@Before
	public void setUp() {
		engine = new Engine();
	}

	@Test
	public void interactWithKeyboard() {
	}

	@Test
	public void testInteractWithInputString() {
		engine.interactWithInputString("n3424s");

	}
}