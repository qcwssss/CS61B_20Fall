import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private class Entry {
        K key;
        V value;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<ArrayList<Entry>> buckets;
    private HashSet<K> keySet;
    private int numOfEntries = 0;
    private int numOfBuckets;
    private double maxAvgBucketSize;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        keySet = new HashSet<>();
        buckets = new ArrayList<>();
        numOfBuckets = initialSize;
        maxAvgBucketSize = loadFactor;
        for (int i = 0; i < numOfBuckets; i++) {
            buckets.add(new ArrayList<Entry>());
        }
    }

    @Override
    public void clear() {
        keySet = new HashSet<>();
        buckets = new ArrayList<>();
        numOfBuckets = 0;
        numOfEntries = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int index = hash(key, numOfBuckets);
        for (Entry entry : buckets.get(index)) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    private int hash(K key, int capacity) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    @Override
    public int size() {
        return numOfEntries;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            update(key, value);
            return;
        }
        if (size() >= maxAvgBucketSize * numOfBuckets) {
            resize(numOfBuckets * 2);
        }
        int index = hash(key, numOfBuckets);
        buckets.get(index).add(new Entry(key, value));
        keySet.add(key);
        numOfEntries += 1;
    }

    private void resize(int capacity) {
        ArrayList<ArrayList<Entry>> newBuckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            newBuckets.add(new ArrayList<>());
        }
        for (K key : this) {
            int index = hash(key, capacity);
            newBuckets.get(index).add(new Entry(key, get(key)));
        }
        this.numOfBuckets = capacity;
        this.buckets = newBuckets;
    }

    private void update(K key, V value) {
        int index = hash(key, numOfBuckets);
        for (Entry entry : buckets.get(index)) {
            if (entry.key.equals(key)) {
                entry.value = value;
            }
        }
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}
