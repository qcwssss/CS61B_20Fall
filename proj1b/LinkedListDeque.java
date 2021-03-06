/** Double ended queue. */
public class LinkedListDeque<T> implements Deque<T>{

    /** nested class */
    private class Node {
        public T data;
        public Node prev;
        public Node next;

        /** Node constructor #1. */
        public Node(T d, Node previous, Node nextPointer) {
            data = d;
            prev = previous;
            next = nextPointer;
        }
        /** Node constructor #2.
         * Only stores data and not point to other nodes.
         */
        public Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }

    }

    /** fields of Deque. */
    private Node first;
    private Node sentinel;
    private int size;

    /** Constructor of a deque with 1 item */
    public LinkedListDeque(T x) {
        sentinel = new Node(null, null, null);
        /** Link the first node to the sentinel. */
        first = new Node(x, sentinel, sentinel);
        /** Link the sentinel to the first node. */
        sentinel.next = first;
        sentinel.prev = first;

        size = 1;
    }

    /**
     * Deep copy a deque.
     *
     * @param other another deque.
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, sentinel, sentinel);

        size = 0;

        for (int i = 0; i < size; i++) {
            addLast((T) other.get(i));
        }
    }

    /**  Constructor to create an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Add item to the first of the queue list.
     *
     * @param item the data of the new Node.
     */
    @Override
    public void addFirst(T item) {
        Node newFirst = new Node(item);
        if (first != null) {
            Node oldFirst = first;
            first = newFirst;
            // link the new first, 4 steps
            first.next = oldFirst;
            first.prev = sentinel;

            sentinel.next = first;
            oldFirst.prev = first;
        } else {
          // when deque is empty
          first = newFirst;
          sentinel.next = first;
          sentinel.prev = first;
          first.prev = sentinel;
          first.next = sentinel;
        }
        size++;
    }

    /**
     * Add a new node with data to the last of Deque.
     *
     * @param item the data of the new Node.
     */
    @Override
    public void addLast(T item) {
        Node temp = sentinel.prev;// the last node before addLast.
        temp.next = new Node(item, temp, sentinel);
        sentinel.prev = temp.next; // points to the new Node
        size++;

    }

    /**
     * Get the size of the Deque.
     *
     * @return size.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Print each item in the Deque.
     */
    @Override
    public void printDeque() {
        // Check first whether the deque is empty.
        if (!isEmpty()) {
            Node temp = sentinel;
            while (temp.next != sentinel) {
                System.out.print(temp.next.data + " ");
                temp = temp.next;
            }
            System.out.print("\n");
            return;
        }
        System.out.println("*** Deque is empty ***");

    }

    /**
     * If the list is not empty,
     * Remove the first item of the deque;
     * otherwise return null.
     *
     * @return the data of the original first node,
     * or null if empty.
     */
    @Override
    public T removeFirst() {
        if (!isEmpty()) {
            //Node temp = sentinel.next; // store old first
            T firstItem = sentinel.next.data;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;

            return firstItem;
        }
        return null;
    }

    /**
     * If the list is not empty, remove the last node.
     * Return the data of the last node.
     * Otherwise return null.
     *
     * @return data of last, or null.
     */
    @Override
    public T removeLast() {
        if (!isEmpty()) {
            // Relocate the last node pointer
            Node temp = sentinel.prev;
            T lastItem = sentinel.prev.data;
            sentinel.prev = temp.prev;
            sentinel.prev.next = sentinel;

            // nullify the pointer of the old last
            temp.prev = null;
            temp.next = null;

            size--;
            return lastItem;
        }
        return null;
    }

    /**
     * Get the value of data in given index.
     *
     * @param index the data we want to get (from 0 to size-1).
     * @return the data.
     */
    @Override
    public T get(int index) {
        if (index > size -1) {
            throw new IllegalArgumentException
                    (" Index must less than size-1! ");
        }
        T item = null;
        Node temp = this.sentinel;
        while (temp.next != sentinel) {
            item = temp.next.data;
            temp = temp.next;
        }

        return item;
    }

    /**
     * Get the item at given index in an recursice way.
     * @param index index
     * @return an item
     */
    public T getRecursive(int index) {
        // Check the index before executes.
        if (index > size -1) {
            return null;
        }
        // Base case
        if (index == 0) {
            return sentinel.next.data;
        }
        Node temp = sentinel.next.next;
        return getTraverse(index - 1, temp);
    }

    /**
     * Helper methods for getRecursive.
     *
     * @param indexII the index of deque
     * @param node the node of deque
     * @return the data in given index
     */
    public T getTraverse(int indexII, Node node) {
        if (indexII == 0) {
            return node.data;
        }
        return getTraverse(indexII - 1, node.next);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        for (int i = 0; i < 6; i++) {
            deque.addLast(i);
            System.out.printf("deque[%d]: %d \n", i, deque.getRecursive(i));
            //System.out.printf("deque[%d]: %d \n", i, deque.get(i));

        }
        while (!deque.isEmpty()) {
            deque.removeLast();
            //deque.removeFirst();
            deque.printDeque();
        }

        deque.printDeque();

    }


}