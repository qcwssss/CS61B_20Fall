public class ArrayDeque<T> {
    private T[] items;
    private int nextLast;
    private int nextFirst;
    private int size;

    // initialize max capacity
    private int capacity = 8;


    /** Constructor to create an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        size = 0;
        // set initial pointer arbitrarily
        nextFirst = capacity/2;
        nextLast = nextFirst + 1;
    }

    /**
     * Getters helper methods.
     *
     * Return the value of nextFirst.
     * @return nextFirst.
     */
    public int getNextFirst() {
        return this.nextFirst;
    }

    /**
     * Return the integer nextLast.
     * @return nextLast.
     */
    public int getNextLast() {
        return this.nextLast;
    }

    /**
     * Get the capacity of this deque.
     * @return capacity.
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Add the item to the nextFirst position.
     * @param item the item will be added.
     */
    public void addFirst(T item) {
        if (!isFull()) {
            if (nextFirst < 0) {
                nextFirst = circulateNext(nextFirst);
            }
            items[nextFirst] = item;
            nextFirst--;
            size++;

            return;
        }
        System.out.println("--Error, deque already full.--");
        System.out.println("--Can't addFirst item: " + item + " --");

    }

    /**
     * Add item to the nextLast position.
     * @param item the item will be added.
     */
    public void addLast(T item) {
        if (!isFull()) {
            if (nextLast > capacity -1) {
                // circulate nextLast to 0
                nextLast = circulateNext(nextLast);
            }
            items[nextLast]  = item;
            nextLast++;
            size++;

            return;
        }
        System.out.println("--Error, deque already full.--");
        System.out.println("--Can't addLast item: " + item + " --");

    }

    /**
     * Circulate nextLast, nextFirst to make the
     * deque functions as a circle array.
     */
    private int circulateNext(int nextIndex){
        if (nextIndex > capacity -1) {
            nextIndex = 0;
        }
        else if (nextIndex < 0) {
            nextIndex = capacity - 1;
        } else {
        String err =
                "---Error, next index is not exhausted yet--- ";
        System.out.println(err);
        }
        return nextIndex;
    }

    /**
     * check whether the index is in the range of
     * valid value, instead of null.
     */
    private void checkIndex() {

    }


    /**
     * Return true if the deque is empty.
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
     * Return true if deque is full, otherwise, false.
     * @return a boolean value.
     */
    public boolean isFull() {
        if (size >= capacity) {
            return true;
        }
        return false;
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
        if (nextFirst > capacity -1) {
            nextFirst++; // reset next
            nextFirst = circulateNext(nextFirst);
        }
        else if (nextFirst == capacity -1) {
            nextFirst = -1;
            T val = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst++;
            size--;
            return val;
        }
        T val = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst++;
        size--;
        return val;
    }

    /**
     * Removes and return the last item.     *
     * @return last item of the circular array.
     */
    public T removeLast() {
        if (isEmpty()) {
            System.out.println("*** Cannot remove an empty deque ***");
            return null;
        }
        if (nextLast < 0) {
            nextLast = circulateNext(nextLast);
        }
        else if (nextLast == 0) {
            nextLast = capacity;
            T val = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast--;
            size--;
            return val;
        }
        T val = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast--;
        size--;
        return val;
    }

    /**
     * Get the value of deque in given index.
     * @param index the index of array deque.
     * @return item.
     */
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        T val = items[index];
        return val;
    }



}
