import java.util.Iterator;

/** Array based list.
 *  @author Josh Hug
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

public class AList<Item> implements List<Item> {
    private Item[] items;
    private int size;


    /**
     * Instantiate an AListIterator object.
     * @return AListIterator
     */
    @Override
    public Iterator<Item> iterator(){
        return new AListIterator();
    }

    /** Nested AList Iterator class */
    private class AListIterator implements Iterator<Item> {
        private int pos;

        /** Constructor. */
        public AListIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public Item next() {
            Item returnItem = items[pos];
            pos++;
            return returnItem;
        }
    }

    /** Creates an empty list. */
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Inserts X into the back of the list. */
    @Override
    public void addLast(Item x) {
        if (size == items.length) {
            resize((int) (size * 1.5));
        }

        items[size] = x;
        size = size + 1;
    }



    /** Returns the item from the back of the list. */
    @Override
    public Item getLast() {
        return items[size - 1];
    }

    /** Gets the ith item in the list (0 is the front). */
    @Override
    public Item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    @Override
    public int size() {
        return size;
    }

    /** Deletes item   from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        Item x = getLast();
        items[size - 1] = null;
        size = size - 1;
        return x;
    }
}
