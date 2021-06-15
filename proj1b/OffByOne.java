/** See if characters are off by one. */
public class OffByOne implements CharacterComparator{

	/**
	 * See if characters are off by one.
	 * @param x char
	 * @param y char
	 * @return boolean
	 */
	@Override
	public boolean equalChars(char x, char y) {
		int diff = x - y;
		if (Math.abs(diff) == 1){
			return true;
		}
		return false;
	}

}
