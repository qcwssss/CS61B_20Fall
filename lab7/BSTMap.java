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
			//this.size = size; // ?
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
		return get((K) key) != null;
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
		if (value == null) {
			//delete(key);
			return;
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
			x.val = val;
		}
		x.size = 1 + size(x.left) + size(x.right);
		return x;

	}

	@Override
	public Set keySet() {
		throw new UnsupportedOperationException();
	}

	// No need to implement
	@Override
	public Object remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object remove(Object key, Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator iterator() {
		throw new UnsupportedOperationException();

	}

	/** Prints out your BSTMap in order of increasing Key.*/
	public void printInOrder() {
		printKey(root);

	}

	// inorder
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
