/** Double ended queue. */
public class LinkedListDeque<InputType> {

    /** nested class */
    private class Node {
        public InputType data;
        public Node prev;
        public Node next;

        /** Node constructor #1. */
        public Node(InputType d, Node previous, Node nextPointer) {
            data = d;
            prev = previous;
            next = nextPointer;
        }
        /** Node constructor #2.
         * Only stores data and not point to other nodes.
         */
        public Node(InputType data) {
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
    public LinkedListDeque(InputType x) {
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
            addLast((InputType) other.get(i));
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
    public void addFirst(InputType item) {
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
    public void addLast(InputType item) {
        Node temp = sentinel.prev;// the last node before addLast.
        temp.next = new Node(item, temp, sentinel);
        sentinel.prev = temp.next; // points to the new Node
        size++;

    }

    /**
     * Test whether the Deque is empty.
     *
     * @return true if it's empty, false otherwise.
     */
    public boolean isEmpty() {
        if (sentinel.next == sentinel || sentinel.prev == sentinel) {
            return true;
        }
        return false;
    }

    /**
     * Get the size of the Deque.
     *
     * @return size.
     */
    public int size() {
        return size;
    }

    /**
     * Print each item in the Deque.
     */
    public void printDeque() {
        // Check first whether the deque is empty.
        if (!isEmpty()) {
            int i = 0;
            Node temp;
            temp = first;
            while (i < size) {
                InputType x = temp.data;
                System.out.print(x + " ");
                temp = temp.next;
                i++;
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
    public InputType removeFirst() {
        if (!isEmpty()) {
            // move first pointer to next
            Node temp = first;
            first = first.next;
            // erase the link of first
            temp.next = null;
            temp.prev = null;

            first.prev = sentinel;
            sentinel.next = first;
            return temp.data;
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
    public InputType removeLast() {
        if (!isEmpty()) {
            // Relocate the last node pointer
            Node temp = sentinel.prev;
            sentinel.prev = temp.prev;
            // nullify the pointer of the old last
            temp.prev = null;
            temp.next = null;

            sentinel.prev.next = sentinel;
            return temp.data;
        }
        return null;
    }

    /**
     * Get the value of data in given index.
     *
     * @param index the data we want to get (from 0 to size-1).
     * @return the data.
     */
    public InputType get(int index) {
        if (index > size -1) {
            throw new IllegalArgumentException
                    (" Index must less than size-1! ");
        }
        InputType item = null;
        Node temp = this.first;
        for (int i = 0; i < index; i++) {
            item = temp.data;
            temp = temp.next;
        }

        return item;
    }

    public InputType getRecursive(int index) {
        // Check the index before executes.
        if (index > size -1) {
            return null;
        }
        // Base case
        if (index == 0) {
            return first.data;
        }

        Node temp = first.next;
        return getTraverse(index - 1, temp);

    }

    /**
     *
     * @param indexII the index of deque
     * @param node the node of deque
     * @return the data in given index
     */
    public InputType getTraverse(int indexII, Node node) {
        if (indexII == 0) {
            return node.data;
        }
        return getTraverse(indexII - 1, node.next);
    }


}