package byow.lab13;

import java.util.Random;

public class MemoryGameTest {
	private MemoryGame game = new MemoryGame(30, 30, 1022);

	public void testDrawFrame() {
		game.drawFrame("hello");
	}

	public static void main(String[] args) {
		MemoryGameTest mt = new MemoryGameTest();
		mt.testDrawFrame();

	}
}