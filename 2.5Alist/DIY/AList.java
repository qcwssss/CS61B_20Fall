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

public class AList {
    private int item[];
    private int size;

    /** Creates an empty list. */
    public AList() {
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return 0;        
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return 0;        
    }

    /** Returns the number of items in the list. */
    public int size() {
        return 0;        
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        return 0;
    }
} 