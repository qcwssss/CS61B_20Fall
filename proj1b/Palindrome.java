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

	private String reverseWord(Deque wordDeque) {
		// Check first whether the deque is empty.
		String reverse = "";
		while (!wordDeque.isEmpty()) {
			reverse += wordDeque.removeLast();

		}
		return reverse;
	}

	public boolean isPalindrome(String word) {
		Deque dequeOfWord = wordToDeque(word);
		String reversed = reverseWord(dequeOfWord);
		if (reversed.equals(word)) {
			return true;
		}
		return false;

	}

}
