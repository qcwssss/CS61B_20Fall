import java.util.Iterator;
import java.util.Set;

public class BSTMap<K,V> implements Map61B{
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

		/** Returns the this Node, or null if it doesn't exist.
		 * Or return left or right node whichever exists. */
		public Node get(K k) {
			if (k != null && k.equals(key)) {
				return this;
			}
			if (left == null && right == null) {
				return null;
			}
			if (left.get(k) != null) {
				return left.get(k);
			}
			return right.get(k);

		}

	}
	@Override
	public void clear() {

	}

	@Override
	public boolean containsKey(Object key) {
		return false;
	}

	@Override
	public Object get(Object key) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public void put(Object key, Object value) {

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

	}
}
