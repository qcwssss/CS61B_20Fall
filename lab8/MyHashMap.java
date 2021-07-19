import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>{
	private static int initialSize = 16;
	private static double loadFactor = 0.75;
	private ArrayList<BucketsList> arrayList;

	public MyHashMap() {
		arrayList = new ArrayList<>(initialSize);
	}

	public MyHashMap(int initialSize) {
		this.initialSize = initialSize;
		arrayList = new ArrayList<>(initialSize);

	}

	public MyHashMap(int initialSize, double loadFactor) {
		this.initialSize = initialSize;
		this.loadFactor = loadFactor;
		arrayList = new ArrayList<>(initialSize);

	}

	//check size & resize

	/**
	 * Represents one box in the array list that stores the key-value pairs
	 * in the dictionary.
	 */
	private class BucketsList {
		K key;
		V val;
		BucketsList next;

		/**
		 * Stores KEY as the key in this key-value pair, VAL as the value, and
		 * NEXT as the next node in the linked list.
		 */
		BucketsList(K k, V v, BucketsList n) {
			key = k;
			val = v;
			next = n;
		}

	}

	/**
	 * Removes all of the mappings from this map.
	 */
	@Override
	public void clear() {

	}

	/**
	 * Returns true if this map contains a mapping for the specified key.
	 *
	 * @param key
	 */
	@Override
	public boolean containsKey(K key) {
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
		return null;
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
}
