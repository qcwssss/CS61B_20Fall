/** See if characters are off by N. */
public class OffByN implements CharacterComparator{
	private int N;

	/**
	 * Constructor.
	 * @param N value off by
	 */
	public OffByN(int N) {
		this.N = N;
	}

	/**
	 * Test if two characters are off by N.
	 * @param x char
	 * @param y char
	 * @return boolean
	 */
	@Override
	public boolean equalChars(char x, char y) {
		int diff = x - y;
		if (Math.abs(diff) == this.N) {
			return true;
		}
		return false;
	}
}
