/** An SLList is a list of integers, which hide us 
 * from the naked truth*/
public class SLList {

    /** nested class */
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    private IntNode first;
    /** The first item (if it's exist) is at sentinal.next.  */
    private IntNode sentinal;
    private int size;

    /** Constructor */
    public SLList(int x) {
        first = new IntNode(x, null);
        sentinal = new IntNode(999, first);
        size ++;
    }

    /** Build an empty list. */
    public SLList() {
        sentinal = new IntNode(999, null);
        size = 0;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        sentinal.next = new IntNode(x, sentinal.next);
        size ++;
    }
    /** Retrieves the front item from the list. */
    public int getFirst() {
        return sentinal.next.item;
    }

    /** Add an item to the last of the list */
    public void addLast(int x) {
        IntNode p = sentinal;
        /** Advance p to the end. */
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
        System.out.println(empty.size());
        empty.addLast(9);



    }
}