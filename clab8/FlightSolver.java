import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2. Assumes valid input, i.e.
 * all Flight start times are >= end times. If a flight starts at the same time as a flight's end
 * time, they are considered to be in the air at the same time.
 */
public class FlightSolver {
  // private PriorityQueue<Flight> pq;
  private int maxPassengers;

  public FlightSolver(ArrayList<Flight> flights) {
    // sweep line
    ArrayList<int[]> weights = new ArrayList<>(flights.size() * 2);
    for (Flight f : flights) {
      weights.add(new int[] {f.startTime, f.passengers});
      weights.add(new int[] {f.endTime, -f.passengers});
    }
    Collections.sort(weights, (a, b) -> (a[0] - b[0])); // ascending order
    // scan
    int count = 0;
    int res = 0; // store max passengers on air
    for (int[] i : weights) {
      count += i[1];
      res = Math.max(res, count);
    }
    maxPassengers = res;
  }

  public int solve() {
    return maxPassengers;
  }
}
