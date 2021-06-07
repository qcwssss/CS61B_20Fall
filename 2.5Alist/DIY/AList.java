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

public class AList<Type> {
    private Type[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = (Type[]) new Object[100];
        size = 0;
    }

    /** Resize the underlying array to the target capacity. */
    public void reSize(int capacity) {
        Type[] a = (Type[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Type x) {
        if (size == items.length) {
            reSize(size + 1);
        }
        items[size] = x;
        size++;
    }

    /** Returns the item from the back of the list. */
    public Type getLast() {
        return items[size - 1];        
    }
    /** Gets the ith item in the list (0 is the front). */
    public Type get(int i) {
        return items[i];        
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;        
    }

    /** Deletes items from back of the list and
      * returns deleted item. */
    public Type removeLast() {
        Type last = this.getLast();
        // items[size] = 0; fine, but unnecessary
        size--;
        return last;
    }
} 