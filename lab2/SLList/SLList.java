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

    public static void main(String[] args) {
        /** Create a list of integers */
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);

       System.out.println(L.getFirst());

    }
}