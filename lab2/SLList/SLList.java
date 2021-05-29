/** An SLList is a list of integers, which hide us 
 * from the naked truth*/
public class SLList {
    private IntNode first;
    private int size;

    /** nested class */
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /** Constructor */
    public SLList(int x) {
        first = new IntNode(x, null);
        size ++;
    }

    /** Build an empty list. */
    public SLList() {
        first = null;
        size = 0;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
        size ++;
    }
    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Add an item to the last of the list */
    public void addLast(int x) {
        IntNode p = first;
        if (first == null) {
            first = new IntNode(x, null);
            size ++;
            return;
        }

        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null) ;
        size ++;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        /** Create a list of integers */
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(20);
        // an empty list
        SLList empty = new SLList();
        empty.addLast(9);

        System.out.println(empty.size());


    }
}