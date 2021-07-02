package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
    // int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        assertEquals(10, arb.capacity());
        assertEquals(0, arb.fillCount());

        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        assertEquals(0, arb.peek());
        assertEquals(10, arb.fillCount());

        for (int i = 0; i < 7; i++) {
            assertEquals(i, arb.dequeue());
        }
      assertEquals(3, arb.fillCount());
      assertEquals(7, arb.peek());
      assertEquals(7, arb.dequeue());
      assertEquals(8, arb.peek());

    }

    @Test
    public void testCircularIndex() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.dequeue();
        assertEquals(2, arb.peek());
        //check index
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5); // out of bounds
        assertEquals(4, arb.fillCount());


    }
}
