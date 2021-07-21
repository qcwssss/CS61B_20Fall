import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V>{
	// initial factor
	private static final int defaultSize = 16;
	private static final double defaultFactor = 0.75;

	private ArrayList<BucketNode> arrayList;
	private double loadFactor;
	private int size = 0; // occupied box

	/** Constructors. */
	public MyHashMap() {
		this(defaultSize, defaultFactor);
	}

	public MyHashMap(int initialSize) {
		this(initialSize, defaultFactor);
	}

	public MyHashMap(int initialSize, double loadFactor) {
		arrayList = new ArrayList<>(initialSize);
		this.loadFactor = loadFactor;

	}

	//check size & resize
	private void checkSize() {
		if (size / arrayList.size() > this.loadFactor) {
			resize();
		}
	}

	private void resize() {
		List newArrlist = new ArrayList(arrayList.size() * 2);
		// shuffle
		BucketNode tempNode;
		for (BucketNode node : arrayList) {
			tempNode = node;
			while (tempNode.next != null) {
				int pos = hash(tempNode.key);
				if (newArrlist.get(pos) == null) {
					newArrlist.set(pos, tempNode.val);
				} else {
					Object t = newArrlist.get(pos);
				}
				tempNode = tempNode.next;
			}
		}
	}

	/**
	 * Represents one box in the array list that stores the key-value pairs
	 * in the dictionary.
	 */
	private class BucketNode {
		K key;
		V val;
		BucketNode next;

		/**
		 * Stores KEY as the key in this key-value pair, VAL as the value, and
		 * NEXT as the next node in the linked list.
		 */
		BucketNode(K k, V v, BucketNode n) {
			key = k;
			val = v;
			next = n;
		}

		/**
		 * Returns the BucketNode in this linked list of key-value pairs whose key
		 * is equal to KEY, or null if no such BucketNode exists.
		 */
		BucketNode get(K k) {
			// Base case: key is in this node
			if (k != null && k.equals(this.key)) {
				return this;
			}
			if (next == null) {
				return null;
			}
			// recursive
			return next.get(k);

		}

	}

	/**
	 * Removes all of the mappings from this map.
	 */
	@Override
	public void clear() {
		size = 0;
		arrayList = null;
	}

	/**
	 * Returns true if this map contains a mapping for the specified key.
	 *
	 * @param key
	 */
	@Override
	public boolean containsKey(K key) {
		for (BucketNode node : arrayList) {
			if (node.get(key) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the value to which the specified key is mapped, or null if this
	 * map contains no mapping for the key.
	 *
	 * @param key
	 */
	@Override
	public V get(K key) {
		if (arrayList == null) {
			return null;
		}
		BucketNode lookup;
		V returnVal = null;
		for (BucketNode node : arrayList) {
			if (node.get(key) != null) {
				lookup = node.get(key);
				returnVal = lookup.val;
			}
		}
		return returnVal;
	}

	/**
	 * Returns the number of key-value mappings in this map.
	 */
	@Override
	public int size() {
		return 0;
	}

	/**
	 * Associates the specified value with the specified key in this map.
	 * If the map previously contained a mapping for the key,
	 * the old value is replaced.
	 *
	 * @param key
	 * @param value
	 */
	@Override
	public void put(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}

		checkSize();
		int idx = hash(key);
		// add new node
		if (!this.containsKey(key)) {
			arrayList.get(idx).next = new BucketNode(key, value, null);
			size++;
		} else {
			// update value
			BucketNode old = arrayList.get(idx).get(key);
			old.val = value;
		}
	}

	/**
	 * Returns a Set view of the keys contained in this map.
	 */
	@Override
	public Set<K> keySet() {
		// create a HashSet instance variable that holds all keys.
		return null;
	}

	/**
	 * Removes the mapping for the specified key from this map if present.
	 * Not required for Lab 8. If you don't implement this, throw an
	 * UnsupportedOperationException.
	 *
	 * @param key
	 */
	@Override
	public V remove(K key) {
		throw new UnsupportedOperationException("remove() is not supported in MyHashMap");
	}

	/**
	 * Removes the entry for the specified key only if it is currently mapped to
	 * the specified value. Not required for Lab 8. If you don't implement this,
	 * throw an UnsupportedOperationException.
	 *
	 * @param key
	 * @param value
	 */
	@Override
	public V remove(K key, V value) {
		throw new UnsupportedOperationException("remove() is not supported in MyHashMap");
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<K> iterator() {
		// create a HashSet instance variable that holds all keys.
		return null;
	}

	/**
	 * hash function for keys - returns value between 0 and array size -1.
	 * @param key
	 * @return hash value of key
	 */
	private int hash(K key) {
		int h = key.hashCode();
		h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
		return h & (arrayList.size() - 1);
	}
}
