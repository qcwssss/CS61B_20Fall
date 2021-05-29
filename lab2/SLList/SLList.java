/** An SLList is a list of integers, which hide us 
 * from the naked truth*/

public class SLList {
    public IntNode first;

    /** Constructor */
    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }
    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Add an item to the last of the list */
    public void addLast(int x) {
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null) ;
    }
/** Seems void type can't use Recursive
 *
    public void addLastRecur(int x) {
        if (first.next = null) {
            first.next = new IntNode(x, null);
        }
        return addLastRecur
    }
 *
 */

    /** Returns the size of the list starting at IntNode p. */
    public static int size(IntNode p) {
        if (p.next == null) {
            return 1;
        }     
        return 1 + size(p.next);
    }

    public int size() {
        return size(first);
    }

    public static void main(String[] args) {
        /** Create a list of integers */
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(20);

       System.out.println(L.getFirst());

    }
}