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
     * Deep copy another deque.
     * @param other deque.
     */
    public ArrayDeque(ArrayDeque other) {
        size = other.size();
        nextFirst = other.getNextFirst();
        nextLast = other.getNextLast();

        items = (T[]) new Object[other.getCapacity()];
        // copy all items from other
        for (int i = 0; i < other.getCapacity(); i++ ) {
            items[i] = (T) other.items[i];
        }
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
     * Get the items of deque.
     * @return items.
     */
    public T[] getItems() {
        return this.items;
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
            items[nextFirst] = item;
            nextFirst--;
            // check after minus
            nextFirst = checkNext(nextFirst);
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
            items[nextLast]  = item;
            nextLast++;
            //check
            nextLast = checkNext(nextLast);
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
   * check whether the nextFirst/nextLast index is in
   * the range of capacity. If so, reset.
   *
   * @param index next index.
   * @return next index.
   */
    private int checkNext(int index) {
        if (index > capacity - 1 || index < 0) {
            index = circulateNext(index);
        }
        return index;
    }

    /**
     * Check whether the two next index are the same.
     * A helper function for resize.
     * @param nextFirst nextFirst.
     * @param nextLast nextLast.
     */
    private void checkNextEqual(int nextFirst, int nextLast) {

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
        if (nextFirst == capacity -1) {
            nextFirst = -1;
        }
        T val = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst++;
        // in case out of bound, reset next index
        nextFirst = checkNext(nextFirst);
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
        if (nextLast == 0) {
          nextLast = capacity;

        }
        T val = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast--;
        nextLast = checkNext(nextLast);
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
