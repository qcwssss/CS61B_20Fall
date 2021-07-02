import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {

	private static final String keyboard =
			"q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
	private static final double CONCERT_A = 440.0;
	//private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

	private static double frequency(int i) {
		double CONCERT_I = CONCERT_A * Math.pow(2, ((double) i - 24.0) / 12.0);
		return CONCERT_I;
	}

	public static void main(String[] args) {
		GuitarString stringA = new GuitarString(CONCERT_A);
		// create 37 GuitarStrings.
		GuitarString[] gsArr = new GuitarString[keyboard.length()];
		for (int i = 0; i < keyboard.length(); i++) {
			gsArr[i] = new GuitarString(frequency(i));
		}

		GuitarString stringI;
		while (true) {
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				int keyIdx = keyboard.indexOf(key);
				if ( keyIdx >= 0 && keyIdx < 37 ) {
					stringI = gsArr[keyIdx];
					stringI.pluck();
				}

  			}
			/* compute the superposition of samples */
			double sample = stringA.sample();
			for (int i = 0; i <keyboard.length(); i++) {
				sample += gsArr[i].sample();
			}
			/* play the sample on standard audio */
			StdAudio.play(sample);

			/* advance the simulation of each guitar string by one step */
			for (int i = 0; i <keyboard.length(); i++) {
				gsArr[i].tic();
			}
		}

	}

}
