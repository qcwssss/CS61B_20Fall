/** Double ended queue. */
public class LinkedListDeque<InputType> {

    /** nested class */
    private class Node {
        public InputType data;
        public Node prev;
        public Node next;

        public Node(InputType d, Node previous, Node nextPointer) {
            data = d;
            prev = previous;
            next = nextPointer;
      }
    }

    /** fields of Deque. */
    private Node first;
    private Node sentinel;
    private int size;

    /** Constructor of Deque with 1 item */
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
     * Add item to the first of the queue list.
     * @param item the data of the new Node.
     */
    public void addFirst(InputType item) {
        first.next = new Node(item, first, sentinel);
        sentinel.prev = first.next;
    }

    /**
     * Add a new node with data to the last of Deque.
     * @param item the data of the new Node.
     */
    public void addLast(InputType item) {
        Node temp = sentinel.prev;// the last node before addLast.
        temp.next = new Node(item, temp, sentinel);
        sentinel.prev = temp.next; // points to the new Node

    }
    public boolean isEmpty() {

    }
    public int size() {

    }
    public void printDeque()
    public InputType removeFirst()
    public InputType removeLast()
    public InputType get(int index)
}