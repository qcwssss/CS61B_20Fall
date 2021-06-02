/** Doubly linked list */
public class DLList<DataType> {

    /** nested class */
    private class StuffNode {
        public DataType item;
        public StuffNode next;

        public StuffNode(DataType i, StuffNode n) {
            item = i;
            next = n;
        }
    }
    private StuffNode first;
    /** The first item (if it's exist) is at sentinal.next.  */
    private StuffNode sentinal;
    private int size;

    /** Constructor */
    public DLList(DataType x) {
        first = new StuffNode(x, null);
        sentinal = new StuffNode(null, first);
        size ++;
    }

    /** Build an empty list. */
    public DLList() {
        sentinal = new StuffNode(null, null);
        size = 0;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(DataType x) {
        sentinal.next = new StuffNode(x, sentinal.next);
        size ++;
    }
    /** Retrieves the front item from the list. */
    public DataType getFirst() {
        return sentinal.next.item;
    }

    /** Add an item to the last of the list */
    public void addLast(DataType x) {
        StuffNode p = sentinal;
        /** Advance p to the end. */
        while (p.next != null) {
            p = p.next;
        }
        p.next = new StuffNode(x, null) ;
        size ++;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /** Create a list of integers */
        DLList L = new DLList(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(20);
        // an empty list
        DLList<Integer> empty = new DLList<>();
        System.out.println(empty.size());
        empty.addLast(9);



    }
}