public class ArrayDeque<T> {
    private T[] items;
    private int nextLast;
    private int nextFirst;
    private int size;
    // Add circular method
    protected static int capacity = 10;
    /** Constructor to create an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        size = 0;
        // set initial pointer arbitrarily
        nextFirst = capacity/2;
        nextLast = nextFirst + 1;
    }

    /**
     * Return the value of nextFirst.
     * @return nextFirst.
     */
    public int getNextFirst() {
        return this.nextFirst;
    }

    /**
     * Add the item to the nextFirst position.
     * @param item
     */
    public void addFirst(T item) {
        if (nextFirst >= 0 && nextFirst < capacity) {
            items[nextFirst] = item;
            size++;
            nextFirst--;
        }
    }

    /**
     * Add item to the nextLast position.
     * @param item
     */
    public void addLast(T item) {
        if (nextLast < capacity && nextLast > 1) {
            items[nextLast]  = item;
            nextLast++;
            size++;
        }
    }

    /**
     * Return true if the deque is full,
     * else, return false.
     * @return boolean value.
     */
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }

    /**
     * Return size.
     * @return size.
     */
    public int size() {
        return size;
    }

    /**
     * Print the items in the deque from
     * first to last.
     */
    public void printDeque() {
        for (T i : items) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    /**
     * Removes and returns the item
     * at the front of the deque.
     * @return the first item.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T val = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst++;
        size--;
        return val;
    }

    /**
     * Removes and return the last item.
     *
     * @return last item of the circular array.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T val = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast--;
        size--;
        return val;

    }

}
