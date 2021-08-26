package bearmaps.proj2d.trie;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
	private Node root;
	private int numOfKeys;



	private class Node {
		private boolean isKey;
		private Hashtable<Character, Node> hashTb;
		//private Node next;

		/*** Node constructor. */
		public Node() {
			this.isKey = false;
			hashTb = new Hashtable<>();
		}

		public Node(boolean b) {
			this.isKey = b;
			hashTb = new Hashtable<>();
		}
	}

	/*** Create a MyTrieSet. */
	public MyTrieSet() {
		numOfKeys = 0;
	}


	/**
	 * Clears all items out of Trie
	 */
	@Override
	public void clear() {
		root = null;
		numOfKeys = 0;

	}

	/**
	 * Returns true if the Trie contains KEY, false otherwise
	 *
	 * @param key
	 */
	@Override
	public boolean contains(String key) {
		// recursive
		return get(root, key,0) != null;

	}

	private Node get(Node x, String key, int d) {
		if (x == null) return null;
		if (d == key.length()) return x;
		char c = key.charAt(d);
		return get(x.hashTb.get(c), key, d + 1);
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
		if (x == null) root = new Node();
		Node cur = root;
		for (int i = 0; i < k.length(); i++) {
			char c = k.charAt(i);
			if (!cur.hashTb.containsKey(c)) {
				cur.hashTb.put(c, new Node());
			}

			// move ptr to cur node
			cur = cur.hashTb.get(c);
		}
		cur.isKey = true; // mark the key
		numOfKeys++;

		return root;
	}

	/**
	 * Returns a list of all words that start with PREFIX
	 *
	 * @param prefix
	 */
	@Override
	public List<String> keysWithPrefix(String prefix) {
		if (!this.contains(prefix)) {
			throw new IllegalArgumentException("prefix: " + prefix + " doesn't exist in this Trie");
		}

		int preLength = prefix.length();
		Node cur = root.hashTb.get(prefix.charAt(0));

		List<String> result = new LinkedList<>();
		for (int i = 1; i < preLength ; i++) {
			cur = cur.hashTb.get(prefix.charAt(i));
		}

		colHelper(result, cur, prefix);
		return result;
	}

	private void colHelper(List<String> res, Node x, String infix) {
		if (x.isKey) {
			res.add(infix);
		}
		for (char d: x.hashTb.keySet()) {
			colHelper(res, x.hashTb.get(d), infix + d);
		}
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
		throw new UnsupportedOperationException();
	}

	public int getNumOfKeys() {
		return numOfKeys;
	}
}
