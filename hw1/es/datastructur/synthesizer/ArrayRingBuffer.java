package es.datastructur.synthesizer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;

    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Return capacity.
     * @return capacity
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Return fillCount.
     * @return fillCount
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        // circulate
        if (last > rb.length - 1) {
            last = 0;
        }
        rb[last] = x;
        fillCount++;
        last++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (isEmpty()) {
            String e = "Ring Buffer underflow";
            throw new RuntimeException(e);
        }
        // circulate
        if (first > rb.length - 1) {
            first = 0;
        }
        T returnVal = rb[first];
        rb[first] = null;
        first++;
        fillCount--;
        return returnVal;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        // circulate
        if (first > rb.length - 1) {
            first = 0;
        }
        T returnVal = rb[first];
        return returnVal;
    }



    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    /** ArrayRingBuffer Iterator class. */
    private class ARingBufferIterator implements Iterator<T> {
        private int idxPos;

        /** Constructor. */
        public ARingBufferIterator() {
            idxPos = first;
        }

        @Override
        public boolean hasNext() {
            int nextPos = idxPos + 1;
            // wrap
            if (nextPos > rb.length - 1) {
                nextPos = 0;
            }
            return (rb[nextPos] != null);

        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            idxPos++;
            return rb[idxPos];
        }
    }

    /**
     * Generate a ArrayRingBuffer Iterator.
     * @return ARingBufferIterator
     */
    @Override
    public Iterator<T> iterator() {
        return new ARingBufferIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayRingBuffer<?> that = (ArrayRingBuffer<?>) o;
        return first == that.first && last == that.last && fillCount == that.fillCount && Arrays.equals(rb, that.rb);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(first, last, fillCount);
        result = 31 * result + Arrays.hashCode(rb);
        return result;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
