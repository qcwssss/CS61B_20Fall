
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class MyTrieSet implements TrieSet61B{
	private Node root;
	private int numOfKeys;


	private class Node {
		private boolean isKey;
		private Hashtable<Character, Node> hashTb;
		private Node next;

		/*** Node constructor. */
		public Node(boolean b) {
			this.isKey = b;
			hashTb = new Hashtable<>();
		}
	}

	/*** Create a MyTrieSet. */
	public MyTrieSet() {
		numOfKeys = 0;
		//root = new Node();
	}


	/**
	 * Clears all items out of Trie
	 */
	@Override
	public void clear() {

	}

	/**
	 * Returns true if the Trie contains KEY, false otherwise
	 *
	 * @param key
	 */
	@Override
	public boolean contains(String key) {
		// recursive
		if (root.table.contains(key) ) {
			return true;
		}
		for (Character c : root.table.keySet()) {
			if (root.table.get(c).next.table.contains(key)) {
				return true;
			} else {

			}
		}
		return false;
	}


	/**
	 * Inserts string KEY into Trie
	 *
	 * @param key
	 */
	@Override
	public void add(String key) {
		if (key == null) {
			throw new IllegalArgumentException("first argument to add() is null");
		}
		root = addHelper(root, key);
	}

	private Node addHelper(Node x, String k) {
		if (x == null) root = new Node(false);
		Node cur = root;
		for (int i = 0; i < k.length(); i++) {
			char c = k.charAt(i);
			if (!cur.hashTb.contains(c)) {
				root.hashTb.put(c, new Node(false));
			}

			// move ptr to cur node
			cur = cur.hashTb.get(c);
		}
		cur.isKey = true; // mark the key
		numOfKeys++;

		return root;
	}

	private Node addHelperRecur(Node x, String k, int diff) {
		// base case 1
		if (x == null) x = new Node(false);
		// base case 2
		if (diff == k.length()) {
			x.hashTb.put(k.charAt(diff), new Node(true));
		}

		char c = k.charAt(diff);
		x.next = addHelperRecur(x.next.)
	}

	/**
	 * Returns a list of all words that start with PREFIX
	 *
	 * @param prefix
	 */
	@Override
	public List<String> keysWithPrefix(String prefix) {
		return null;
	}

	/**
	 * Returns the longest prefix of KEY that exists in the Trie
	 * Not required for Lab 9. If you don't implement this, throw an
	 * UnsupportedOperationException.
	 *
	 * @param key
	 */
	@Override
	public String longestPrefixOf(String key) {
		return null;
	}
}
