package bearmaps;

import java.util.ArrayList;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
	ArrayList<T> heap;
	ArrayList<Double> priorityAList;

	/** Constructor, create a empty ArrayHeapMinPQ. */
	public ArrayHeapMinPQ() {
		heap = new ArrayList<>();
		priorityAList = new ArrayList<>();
		// Leaving one empty spot, for private parent, left/right child method
		heap.set(0,null);
		priorityAList.set(0, -1.0);

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

		T temp = heap.get(x1);
		heap.set(x1, heap.get(x2));
		heap.set(x2, temp);
	}

	private void swim(int k) {
		if (priorityAList.get(k) < priorityAList.get(parent(k))) {
			swap(k, parent(k));
			swim(parent(k));
		}
	}

	private void sink(int k) {
		if ( priorityAList.get(leftChild(k)) > priorityAList.get(rightChild(k)) ) {
			swap(k, rightChild(k));
		} else {
			swap(k, leftChild(k));
		}
		if (priorityAList.get(k) < priorityAList.get(parent(k))) {
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
		heap.add(item);
		priorityAList.add(priority);

		swim(heap.indexOf(item));
		swim(priorityAList.indexOf(priority));

	}

	@Override
	public boolean contains(T item) {
		return heap.contains(item);
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
		return heap.size();
	}

	@Override
	public void changePriority(T item, double priority) {
		int index = heap.indexOf(item);
		priorityAList.set(index, priority);
	}
}
