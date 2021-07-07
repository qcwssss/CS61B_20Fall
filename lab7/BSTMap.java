import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B{
	private Node root;

	/** Represents one node in a binary search tree, which stores
	 * the key-value pairs in the dictionary. */
	private class Node {
		int size = 0; // size of subtree

		private K key;
		private V val;
		private Node left, right;

		/** Binary Search Tree Constructor. */
		public Node(K k, V v) {
			key = k;
			val = v;
			size++;
		}
	}

	@Override
	public void clear() {
		root.size = 0;
		root = null;

	}

	@Override
	public boolean containsKey(Object key) {
		if (key == null) {
			throw new IllegalArgumentException("calls containsKey() with a null key");
		}
		return get(key) != null;
	}

	@Override
	public Object get(Object key) {
		return get(root, (K) key);
	}

	/* Returns the this Node, or null if it doesn't exist.
	 * Or return left or right node whichever exists. */
	private V get(Node x, K k) {
		if (k == null) {
			throw new IllegalArgumentException("calls get() with a null key");
		}
		if (x == null) {
			return null;
		}
		// binary search for key
		int compare = k.compareTo(x.key);
		if (compare < 0) {
			return get(x.left, k);
		}
		else if (compare > 0) {
			return get(x.right, k);
		} else {
			return x.val;
		}
	}

	@Override
	public int size() {
		return size(root);
	}

	/*  return number of key-value pairs in BST rooted at x. */
	private int size(Node x) {
		if (x == null) {
			return 0;
		} else {
			return x.size;
		}
	}

	/** Inserts the key-value pair of KEY and VALUE into this dictionary,
	 *  replacing the previous value associated to KEY, if any. */
	@Override
	public void put(Object key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("calls put() with a null key");
		}

		root = put(root, (K) key, (V) value);

	}

	private Node put(Node x, K key, V val) {
		if (x == null) {
			return new Node(key, val);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		}
		else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val= val;
		}
		x.size = 1 + size(x.left) + size(x.right);
		return x;

	}

	/**
	 * Reference Solution:
	 * @Source https://github.com/chenyuxiang0425/cs61b_sp19/blob/master/lab7/BSTMap.java
	 */

	@Override
	public Set keySet() {
		//throw new UnsupportedOperationException();
		Set<K> BSTSet = new HashSet<>();
		traverseIn(root, BSTSet);
		return BSTSet;
	}

	// Inorder traversal
	private void traverseIn(Node x, Set s) {
		Node temp = x; // temp tree node pointer
		if (temp == null) {
			return;
		}
		traverseIn(temp.left, s);
		s.add(temp.key);
		traverseIn(temp.right, s);

	}

	// Remove remain unfilled
	/*
	Deletion key has no children.
	Deletion key has one child.
	Deletion key has two children.
	 */

	/**
	 * Remove node, case 1 key 1 value.
	 * @param key key
	 * @return value
	 */
	@Override
	public Object remove(Object key) {
		// Deletion key doesn't exist
		if (!containsKey(key)) {
			throw new UnsupportedOperationException();
		}
		Object returnVal = get(key);
		root = remove(root, (K) key);
		return returnVal;

	}

	/**
	 * Get node with a given key, a helper method.
	 * @param x node
	 * @param k key
	 * @return node
	 */
	private Node getNode(Node x, K k) {
		if (k == null) {
			throw new IllegalArgumentException("calls get() with a null key");
		}
		if (x == null) {
			return null;
		}
		// binary search for key
		int compare = k.compareTo(x.key);
		if (compare < 0) {
			return getNode(x.left, k);
		}
		else if (compare > 0) {
			return getNode(x.right, k);
		} else {
			return x;
		}
	}

	/**
	 * Remove node, one key many values.
	 * @param key key
	 * @param value value
	 * @return value
	 */
	@Override
	public Object remove(Object key, Object value) {
		if (!containsKey(key)) {
			//throw new UnsupportedOperationException("can't remove() a null key");
			return null;
		}
		if (!get(key).equals(value)) {
			//throw new UnsupportedOperationException("value given doesn't match key pairs");
			return null;
		}
		root = remove(root, (K)key);
		return value;
	}

	/**
	 * Really works remove() method.
	 * @param x x
	 * @param key key
	 * @return root
	 */
	private Node remove(Node x, K key) {
		if (x == null || !containsKey(key)) {
			return null;
		}
		Node temp = x;
		int cmp = key.compareTo(x.key);

		if (cmp < 0) { // key is smaller
			temp.left = remove(temp.left, key);
		}
		else if (cmp > 0) { // key is larger
			temp.right = remove(temp.right, key);
		} else { // find the key
			//1. Deletion key has no children.
			if (temp.left == null && temp.right == null) {
				return null;
			}
			//2. Deletion key has one child.
			if (temp.left == null) {
				return temp.right;
			}
			if (temp.right == null) {
				return temp.left;
			}
			//3. Deletion key has two child.
			// find predecessor (or successor)
			// save the link points to the predecessor
			// delete predecessor, points the link to the left node of the predecessor
			// set the root node as the predecessor

			temp = predecessor(temp.left); // temp points to the predecessor
			temp.left = deletePred(x.left);
			temp.right = x.right;

		}
		temp.size = 1 + size(temp.left) + size(temp.right);
		return temp;
	}

	/** Find predecessor node. */
	private Node predecessor(Node x) { // input x = root.left
		Node temp = x;
		if (temp.right == null) {
			return temp;
		}
		return predecessor(temp.right);
	}

	/** Delete predecessor node. */
	private Node deletePred(Node x) { // x = root.left
		Node temp = x;
		if (temp.right == null) {
			return temp.left;
		}
		temp.right = deletePred(temp.right);
		temp.size = 1 + size(temp.right) + size(temp.left);
		return temp;

	}

	@Override
	public Iterator iterator() {
		return new BSTMapIter();
	}

	private class BSTMapIter implements Iterator{
		private Node cur;

		/** Create a new BSTMapIter by setting cur to the root node in the
		 *  BSTree that stores the key-value pairs. */
		public BSTMapIter() {
			cur = root;
		}

		@Override
		public boolean hasNext() {
			return cur != null;
		}

		@Override
		public Object next() {
			K next = cur.key;
			if (cur.left != null) {
				cur = cur.left;
			}
			else if (cur.right != null) {
				cur = cur.right;
			}
			return next;
		}
	}

	/** Prints out your BSTMap in order of increasing Key.*/
	public void printInOrder() {
		printKey(root);
	}

	// Inorder traversal
	private void printKey(Node x) {
		Node temp = x; // temp tree node pointer
		if (temp == null) {
			return;
		}
		printKey(temp.left);
		System.out.println(temp.key);
		printKey(temp.right);

	}

}
