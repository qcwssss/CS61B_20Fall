package list;

/**
 * List interface.
 * @author Chen Qiu
 * @param <Item> any data type
 */
public interface List61B<Item> {

	void addLast(Item x);
	Item getLast();
	int size();
	Item get(int index);
}
