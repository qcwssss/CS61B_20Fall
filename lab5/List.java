import java.awt.event.ItemEvent;
import java.util.Iterator;

/**
 * ListTime interface.
 * @author Chen Qiu
 * @param <Item> any data type
 */
public interface ListTime<Item> extends Iterable<Item> {

	void addLast(Item x);
	Item getLast();
	int size();
	Iterator<Item> iterator();

}
