package bearmaps;

import java.util.ArrayList;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
	ArrayList<T> heap;
	ArrayList<Double> priorityAList;

	/** Constructor, create a empty ArrayHeapMinPQ. */
	public ArrayHeapMinPQ() {
		heap = new ArrayList<>();
		priorityAList = new ArrayList<>();

	}


	@Override
	public void add(T item, double priority) {
		if (this.contains(item)) {
			throw new IllegalArgumentException("** item already exists **\n");
		}
		heap.add(item);
		priorityAList.add(priority);

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
