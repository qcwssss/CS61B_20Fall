import java.util.Iterator;

/** An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within. */
public class SLList<Item> implements List<Item>{
	private class IntNode {
		public Item item;
		public IntNode next;

		public IntNode(Item i, IntNode n) {
			item = i;
			next = n;
		}
	}

	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;

	/** Creates an empty SLList. */
	public SLList() {
		sentinel = new IntNode(null, null);
		size = 0;
	}

	public SLList(Item x) {
		sentinel = new IntNode(null, null);
		sentinel.next = new IntNode(x, null);
		size = 1;
	}

	/** Adds x to the front of the list. */
	public void addFirst(Item x) {
		sentinel.next = new IntNode(x, sentinel.next);
		size = size + 1;
	}

	/** Returns the first item in the list. */
	public Item getFirst() {
		return sentinel.next.item;
	}

	/** Adds x to the end of the list. */
	@Override
	public void addLast(Item x) {
		size = size + 1;

		IntNode p = sentinel;

		/* Advance p to the end of the list. */
		while (p.next != null) {
			p = p.next;
		}

		p.next = new IntNode(x, null);
	}

	/** returns last item in the list */
	@Override
	public Item getLast() {
		size = size + 1;

		IntNode p = sentinel;

		/* Advance p to the end of the list. */
		while (p.next != null) {
			p = p.next;
		}

		return p.item;
	}


	/** Returns the size of the list. */
	@Override
	public int size() {
		return size;
	}

	@Override
	public Item get(int index) {
		int counter = -1; // index starts from 0
		IntNode temp = sentinel;
		while(counter < index) {
			temp = temp.next;
			counter++;
		}

		return temp.item;
	}

	@Override
	public Iterator<Item> iterator() {
		return null;
	}

	public static void main(String[] args) {
		/* Creates a list of one integer, namely 10 */
		SLList L = new SLList();
		for (int i = 0; i < 5; i++) {
			L.addLast(i);
			System.out.println(L.get(i));

		}
		System.out.print("size: ");
		System.out.println(L.size());

	}
}