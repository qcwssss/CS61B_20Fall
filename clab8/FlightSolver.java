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
  private int maxPassengers;

  /**
   * Constructor, create a FlightSolver.
   *
   * @param flights
   */
  public FlightSolver(ArrayList<Flight> flights) {
    /** sweep line */
    /*
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
    */

    /** Priority Queue */
    PriorityQueue<Flight> startMinPQ = new PriorityQueue<>((a, b) -> (a.startTime - b.startTime));
    PriorityQueue<Flight> endMinPQ = new PriorityQueue<>((a, b) -> (a.endTime - b.endTime) );

    startMinPQ.addAll(flights);
    endMinPQ.addAll(flights);
    // print pq
    System.out.println("startMinPQ: " + startMinPQ);
    for (Flight f : startMinPQ) {
      System.out.print(f.startTime + " ");
    }
    System.out.println("\nendMinPQ: " + endMinPQ);
    for (Flight f : endMinPQ) {
      System.out.print(f.endTime + " ");
    }
    System.out.println("\n-----------------------\n");

    int nowPassengers = 0; // To store current timestamp passenger number
    while (startMinPQ.peek() != null) {
      // compare the smallest startTime with the smallest endTime
      if (startMinPQ.peek().startTime <= endMinPQ.peek().endTime) {
        nowPassengers += startMinPQ.poll().passengers;
        if (nowPassengers > maxPassengers) maxPassengers = nowPassengers; // maintain the max value
      } else {
        // endTime < start time, a plane landed
        nowPassengers -= endMinPQ.poll().passengers;
      }
    }
  }

  public int solve() {
    return maxPassengers;
  }
}
