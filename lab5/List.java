import java.awt.event.ItemEvent;
import java.util.Iterator;

/**
 * List interface.
 * @author Chen Qiu
 * @param <Item> any data type
 */
public interface List<Item> extends Iterable<Item> {

	void addLast(Item x);
	Item getLast();
	int size();
	Item get(int index);

}
