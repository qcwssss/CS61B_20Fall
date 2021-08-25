package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
	ArrayList<PNode> heap;
	Hashtable<T, Integer> indexMap;

	/** Constructor, create a empty ArrayHeapMinPQ. */
	public ArrayHeapMinPQ() {
		heap = new ArrayList<>();
		// Leaving one empty spot, for private parent, left/right child method
		heap.add(null);
		indexMap = new Hashtable<>();// for contains() only?

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

	private void swap(int x1, int x2) {
		checkIndex(x1);
		checkIndex(x2);

		PNode temp = heap.get(x1);
		heap.set(x1, heap.get(x2));
		heap.set(x2, temp);
		// swap index
		indexMap.put(heap.get(x1).item, x1);
        indexMap.put(heap.get(x2).item, x2);
	}

	private void swim(int k) {
		//checkIndex(k);
		if (k>1 && getPriority(k) < getPriority(parent(k))) {
			swap(k, parent(k));
			swim(parent(k));
		}
	}



	private void checkIndex(int x) {
    if (x == 0 || x >= heap.size()) {
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
		if (heap.isEmpty()) {
			throw new NoSuchElementException("ArrayHeap is empty!");
		}
		return heap.get(1).item;
	}

	@Override
	public T removeSmallest() {
		if (heap.size() == 1) {
			throw new NoSuchElementException("ArrayHeap is empty!");
		}
		T retrunVal = getSmallest();
		heap.set(1, heap.get(heap.size() - 1));

		indexMap.remove(retrunVal);
		sink(1); // sink to the smaller sub-node
		if (indexMap.size() != 0) {
			indexMap.put(heap.get(1).item, 1);
		}

		heap.remove(this.size());
		return retrunVal;
	}

	private void sink(int k) { // k = 1
		// base case
		/* Only need to sink nodes with both left and right children. */
		int smaller = smallerChild(k);

		if ( getPriority(k) > getPriority(smaller) ) {
			swap(k, smaller);
			sink(smaller);
		}

	}

	private int smallerChild(int k) {
		int leftChild = leftChild(k);
		leftChild = leftChild <= size() ? leftChild : k;

		int rightChild = rightChild(k);
		rightChild = rightChild <= size() ? rightChild : leftChild;
		return heap.get(leftChild).getPriority() < heap.get(rightChild).getPriority()
				? leftChild : rightChild;
	}

	@Override
	public int size() {
		return heap.size() - 1;
	}

	@Override
	public void changePriority(T item, double priority) {
		if (!this.contains(item)) {
			throw new NoSuchElementException("item doesn't exist in heap");
		}
		int idx = indexMap.get(item);
		heap.set(idx, new PNode(item, priority) );
		// compare priority with parents and children
		double parentPrior, childPrior, curPrior;
		curPrior = getPriority(idx);
		/*
		if (hasParent(idx)){
			parentPrior = getPriority(parent(idx));
		} else {
			parentPrior = getPriority(idx);
		}
		*/
		parentPrior = hasParent(idx) ? getPriority(parent(idx)) : curPrior;
		childPrior = getPriority(smallerChild(idx));
		if (curPrior < parentPrior) {
			swim(idx);
		} else if (curPrior > childPrior) {
			sink(idx);
		}
	}

	private boolean hasParent(int k) {
		checkIndex(k);
		return k > 1;
	}

	/** PNode class, which will be stored in ArrayHeapMinPQ's ArrayList.*/
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
