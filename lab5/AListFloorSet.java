/**
 * TODO: Fill in the add and floor methods.
 */
public class AListFloorSet implements Lab5FloorSet {
    AList<Double> items;

    public AListFloorSet() {
        items = new AList<>();
    }

    public void add(double x) {
        items.addLast(x);
    }

    public double floor(double x) {
        double best = Double.NEGATIVE_INFINITY;
        for (Double num : items) {
            if (num < x) {
              if (best < num) {
                  best = num;
              }
            }
        }
        return best;
    }
}
