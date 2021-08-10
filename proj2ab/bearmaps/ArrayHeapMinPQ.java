package bearmaps;

import java.util.ArrayList;
import java.util.Hashtable;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
	ArrayList<PNode> heap;
	Hashtable<T, Integer> indexMap;
	//ArrayList<Double> priorityAList;

	/** Constructor, create a empty ArrayHeapMinPQ. */
	public ArrayHeapMinPQ() {
		heap = new ArrayList<>();
		// Leaving one empty spot, for private parent, left/right child method
		heap.add(null);
		indexMap = new Hashtable<>();

	}

	private double getPriority(int k){
		checkIndex(k);
		return this.heap.get(k).getPriority();
	}

	/** Helper methods to find parent, child index. */
	int parent(int k) {
		checkIndex(k);
		return k/2;
	}

	int rightChild(int k) {
		checkIndex(k);
		return 2*k + 1;
	}

	int leftChild(int k) {
		checkIndex(k);
		return 2*k;
	}

	void swap(int x1, int x2) {
		checkIndex(x1);
		checkIndex(x2);

		PNode temp = heap.get(x1);
		heap.set(x1, heap.get(x2));
		heap.set(x2, temp);
	}

	private void swim(int k) {
		if (k>1 && getPriority(k) < getPriority(parent(k))) {
			swap(k, parent(k));
			swim(parent(k));
		}
	}

	private void sink(int k) {
		if ( getPriority(leftChild(k)) > getPriority(rightChild(k)) ) {
			swap(k, rightChild(k));
		} else {
			swap(k, leftChild(k));
		}
		if (getPriority(k) < getPriority(parent(k)) ) {
			swap(k, parent(k));
			swim(parent(k));
		}

	}

	private void checkIndex(int x) {
		if (x == 0) {
			throw new IllegalArgumentException("Invalid index, 0 cell is a fixed empty cell");
		}
	}


	@Override
	public void add(T item, double priority) {
		if (this.contains(item)) {
			throw new IllegalArgumentException("** item already exists **\n");
		}
		PNode added = new PNode(item, priority);
		heap.add(added);
		swim(heap.size() -1);
		int idxAdded = heap.indexOf(added);
		// add new item index to idxMap
		indexMap.put(item, idxAdded);
	}

	@Override
	public boolean contains(T item) {
		return indexMap.containsKey(item);
	}

	@Override
	public T getSmallest() {
		return null;
	}

	@Override
	public T removeSmallest() {
		return null;
	}

	@Override
	public int size() {
		return heap.size() - 1;
	}

	@Override
	public void changePriority(T item, double priority) {
		//heap.indexOf(new PNode(item, 1));
	}

	/**
	 * PNode class, which will be stroed in ArrayHeapMinPQ's ArrayList.
	 */
	private class PNode {
		T item;
		double priority;


		/** Creates a PNode. */
		private PNode(T item, double p) {
			this.item = item;
			this.priority = p;
		}

		private double getPriority() {
			return this.priority;
		}
	}
}
