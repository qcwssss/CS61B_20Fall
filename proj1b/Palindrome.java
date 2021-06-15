public class Palindrome {

	/**
	 * Turn a string into a linked list Deque,
	 * which stores every characters.
	 * @param word a string
	 * @return LinkedListDeque
	 */
	public Deque<Character> wordToDeque(String word) {
		LinkedListDeque<Character> text = new LinkedListDeque<>();
		for (int i = 0; i <word.length(); i++) {
			text.addLast(word.charAt(i));
		}
		return text;
	}

	/**
	 * Helper method for execute isPalindrome recursively.
	 * @param deque deque of characters
	 * @return a word string
	 */
	private String dequeToString(Deque deque) {
		String word = "";
		while(!deque.isEmpty()) {
			word += deque.removeFirst();
		}
		return word;
	}

	/**
	 * Test if word is palindrome
	 * @param word word string
	 * @return true or false
	 */
	public boolean isPalindrome(String word) {
		Deque dequeOfWord = wordToDeque(word);
		if (dequeOfWord.size() == 0 || dequeOfWord.size() == 1){
			return true;
		}
		// recursive
		if (dequeOfWord.removeLast() == dequeOfWord.removeFirst()) {
			return isPalindrome(dequeToString(dequeOfWord));
		}
		return false;
	}

	/**
	 * Overload:
	 * Test if the word is a palindrome according to the character comparison.
	 * @param word a word string
	 * @param cc CharacterComparator class
	 * @return true or false
	 */
	public boolean isPalindrome(String word, CharacterComparator cc) {
		Deque dequeOfWord = wordToDeque(word);
		if (dequeOfWord.size() == 0 || dequeOfWord.size() == 1){
			return true;
		}

		if (dequeOfWord.size() > 1) {
			if (cc.equalChars((char)dequeOfWord.removeFirst(), (char)dequeOfWord.removeLast())){
				return isPalindrome(dequeToString(dequeOfWord), cc);
			}
		}
		return false;
	}

}
