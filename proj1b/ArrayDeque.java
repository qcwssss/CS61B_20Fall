public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int nextLast;
    private int nextFirst;
    private int size;

    // initialize max capacity
    private int capacity = 8;
    private static int REFACTOR = 2;


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
    @Override
    public void addFirst(T item) {
        if (isFull()) {
          resize(capacity * REFACTOR);
        }
        items[nextFirst] = item;
        nextFirst--;
        // check after minus
        nextFirst = checkNext(nextFirst);
        size++;

    }

    /**
     * Add item to the nextLast position.
     * @param item the item will be added.
     */
    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(capacity * REFACTOR);
        }
        items[nextLast]  = item;
        nextLast++;
        //check
        nextLast = checkNext(nextLast);
        size++;

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
     * Check capacity usage, shrink when necessary.
     */
    public void checkSizeUsage() {
        if (getCapacity() > 16 && size() < getCapacity() / 4) {
            // shrink deque, make usage of 25% of new capacity
            int newCapacity = size() * 4;
            T[] temp = (T[]) new Object[newCapacity];
            System.arraycopy(items, 0, temp, 0, newCapacity);

            // reset attributes
            items = temp;
            capacity = newCapacity;
            // set new bound
            if (nextFirst > capacity) {
                nextFirst = capacity - 1;
            }
            else if (nextLast > capacity) {
                nextLast = capacity - 1;
            }
        }
    }

    /**
     * Resize the capacity when full.
     * @param newCapacity new capacity.
     */
    public void resize(int newCapacity) {
        T[] temp = (T[]) new Object[newCapacity];
        System.arraycopy(items, 0, temp, 0, capacity);
        items = temp;
        // set new next index
        nextLast = capacity;
        nextFirst = newCapacity - 1;
        this.capacity = newCapacity;
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
    @Override
    public int size() {
        return size;
    }

    /**
     * Print the items in the deque from
     * first to last.
     */
    @Override
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
    @Override
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

        checkSizeUsage();// check capacity usage
        return val;
    }

    /**
     * Removes and return the last item.
     * @return last item of the circular array.
     */
    @Override
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

        checkSizeUsage();// check capacity usage
        return val;
    }

    /**
     * Get the value of deque in given index.
     * @param index the index of array deque.
     * @return item.
     */
    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        T val = items[index];
        return val;
    }



}
