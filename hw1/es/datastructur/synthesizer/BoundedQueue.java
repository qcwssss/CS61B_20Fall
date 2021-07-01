package es.datastructur.synthesizer;

public interface BoundedQueue<T> {
  /**
   * Return size of the buffer.
   * @return size
   */
  int capacity();

  /**
   * Return number of items currently in the buffer.
   * @return number of items
   */
  int fillCount();

  /**
   * Add item x to the end.
   * @param x item will be added
   */
  void enqueue(T x);

  /**
   * Delete and return item from the front.
   * @return deleted item.
   */
  T dequeue();

  /**
   * Return (but do not delete) item from the front
   * @return items
   */
  T peek();

  /**
   * See if a BoundedQueue is empty.
   * @return a boolean
   */
  default boolean isEmpty() // is the buffer empty (fillCount equals zero)?
      {
    if (fillCount() == 0) {
      return true;
    }
    return false;
  }

  /**
   * See if a BoundedQueue is full.
   * @return a boolean
   */
  default boolean isFull() // is the buffer full (fillCount is same as capacity)?
      {
    if (fillCount() == capacity()) {
      return true;
    }
    return false;
  }
}
