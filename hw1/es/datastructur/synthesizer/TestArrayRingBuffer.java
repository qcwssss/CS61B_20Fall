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
        //int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        assertEquals(0, arb.peek());

        for (int i = 0; i < 7; i++) {
            assertEquals(i, arb.dequeue());
        }
        assertEquals(7, arb.peek());

    }
}
