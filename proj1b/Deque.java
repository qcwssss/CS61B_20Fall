public interface Deque<T> {

	public void addFirst(T item);
	public void addLast(T item);

	/**
	 * Default method, test if the deque is empty.
	 * @return true or false
	 */
	default public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	/** Returns the number of items in the deque.*/
	public int size();

	/** Prints the items in the deque from first to last. */
	public void printDeque();

	/** Removes and returns the item at the front of the deque.
	 * If no such item exists, returns null.
	 */
	public T removeFirst();

	/**
	 * Removes and returns the item at the back of the deque.
	 * If no such item exists, returns null.
	 */
	public T removeLast();

	/**
	 * Gets the item at the given index,
	 * where 0 is the front, 1 is the next item.
	 */
	public T get(int index);
}
