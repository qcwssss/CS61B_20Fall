package byow.Core;


import org.junit.Before;
import org.junit.Test;

public class EngineTest {


	@Test
	public void interactWithKeyboard() {
	}



	public static void main(String[] args) {
		Engine engine = new Engine();
		String s1 = "\uE108 \u0005t HLN3424322SWWWWAAAAAAAWWWWWWWWWWWDDDDDDDWWWWWWAAAWAAAAAAAAASDSSSAAAAAAAAA";
		String s2 = "N3424322SWWWWAAAAAAAWWWWWWWWWWWDDDDDDDWWWWWWAAAWAAAAAAAAASDSSSAAAAAAAAA";


		//engine.interactWithInputString("n3424sddw");
		//engine.interactWithInputString("n3424s");
		//engine.interactWithKeyboard();
		engine.interactWithInputString(s1);
		//engine.interactWithInputString(s2);



	}
}