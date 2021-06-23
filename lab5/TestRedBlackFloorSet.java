import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
       // TODO: YOUR CODE HERE
        //constant
        double bound = 5000;
        int total = 200 * (int)bound;
        AListFloorSet floorSetA = new AListFloorSet();
        RedBlackFloorSet randRed = new RedBlackFloorSet();

        for (int i = 0; i < total; i++) {
            double rand = StdRandom.uniform(-bound, bound);
            floorSetA.add(rand);
            randRed.add(rand);
        }

        for (int j = 0; j < 100000; j++) {
            double rand = StdRandom.uniform(-bound, bound);
            assertEquals(floorSetA.floor(rand),  randRed.floor(rand), 0.000001);
        }
    }
}
