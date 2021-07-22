import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V>{
	// initial factor
	private static final int defaultSize = 16;
	private static final double defaultFactor = 0.75;

	private BucketNode<K, V>[] bucketsList;
	private double loadFactor;
	private int tableSize;
	private int size = 0; // occupied box

	/** Constructors. */
	public MyHashMap() {
		this(defaultSize, defaultFactor);
	}

	public MyHashMap(int initialSize) {
		this(initialSize, defaultFactor);
	}

	public MyHashMap(int initialSize, double loadFactor) {
		bucketsList = new BucketNode[initialSize];
		this.loadFactor = loadFactor;
		tableSize = initialSize;

	}

	//check size & resize
	private void checkSize() {
		if (size / tableSize > this.loadFactor) {
			resize();
		}
	}

	private void resize() {
		int newSize = tableSize * 2;// scale
		BucketNode<K, V>[] largerList = new BucketNode[newSize];
		// shuffle
		BucketNode<K, V> tempNode;
		for (BucketNode<K, V> node : bucketsList) {
			tempNode = node;
			while (tempNode!= null) {
				int pos = hash(tempNode.key, newSize );
				if (largerList[pos] == null) {
					largerList[pos] = new BucketNode(tempNode.key, tempNode.val, null);
				} else {
					// add to the end of Linked Lis
					largerList[pos].addLast(largerList[pos], tempNode.key, tempNode.val);
				}
				tempNode = tempNode.next;
			}
		}
	}

	/**
	 * Represents one box in the array list that stores the key-value pairs
	 * in the dictionary.
	 */
	private class BucketNode<K, V> {
		K key;
		V val;
		BucketNode<K, V> next;

		/**
		 * Stores KEY as the key in this key-value pair, VAL as the value, and
		 * NEXT as the next node in the linked list.
		 */
		BucketNode(K k, V v, BucketNode<K, V> n) {
			key = k;
			val = v;
			next = n;
		}

		/**
		 * Returns the BucketNode in this linked list of key-value pairs whose key
		 * is equal to KEY, or null if no such BucketNode exists.
		 */
		BucketNode<K, V> get(K k) {
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

		BucketNode<K, V> addLast(BucketNode node, K k, V v) {
			// 2 base case
			if (node == null) {
				return new BucketNode<>(k, v, null);
			}
			// update the value of the same key
			if (node.key.equals(k)) {
				node.val = v;
				return node;
			}
			// recursive, add to the end
			return addLast(node.next, k, v);

		}

	}

	/**
	 * Removes all of the mappings from this map.
	 */
	@Override
	public void clear() {
		size = 0;
		bucketsList = null;
	}

	/**
	 * Returns true if this map contains a mapping for the specified key.
	 *
	 * @param key
	 */
	@Override
	public boolean containsKey(K key) {
		for (BucketNode<K, V> node : bucketsList) {
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
		if (bucketsList == null) {
			return null;
		}
		BucketNode<K, V> lookup;
		V returnVal = null;
		for (BucketNode<K, V> node : bucketsList) {
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
		int idx = hash(key, tableSize);
		// add new node
		if (!this.containsKey(key)) {
			bucketsList[idx].next = new BucketNode<K, V>(key, value, null);
			size++;
		} else {
			// update value
			BucketNode<K, V> old = bucketsList[idx].get(key);
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
	private int hash(K key, int capacity) {
		return (key.hashCode() & 0x7fffffff) % capacity;
	}
}
