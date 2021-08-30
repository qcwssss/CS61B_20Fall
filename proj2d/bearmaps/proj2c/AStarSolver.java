package bearmaps.proj2c;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{
	private SolverOutcome outcome;
	private double solutionWeight;
	private LinkedList<Vertex> solution;
	private double timeSpent;
	private int numOfDequeue;

	Map<Vertex, Double> distTo;
	Map<Vertex, Vertex> edgeTo;

	public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){

		this.solution = new LinkedList<>();
		solutionWeight = 0;
		numOfDequeue = 0;

		ExtrinsicMinPQ<Vertex> fringe = new ArrayHeapMinPQ<>();
		this.distTo = new HashMap<>();
		this.edgeTo = new HashMap<>(); // <to, from>

		distTo.put(start, 0.0);
		fringe.add(start, input.estimatedDistanceToGoal(start, end));

		Stopwatch watch = new Stopwatch();

		while (fringe.size() > 0) {
			// solvable
			if (fringe.getSmallest().equals(end)) {
				Vertex ptr = fringe.getSmallest();

				solution.add(ptr); // last node
				while (!ptr.equals(start)) {
					ptr = edgeTo.get(ptr);
					solution.addFirst(ptr);
				}
				outcome = SolverOutcome.SOLVED;
				solutionWeight = distTo.get(end);
				timeSpent = watch.elapsedTime();

				return;
			}

			// time out
			if (watch.elapsedTime() > timeout ) {
				outcome = SolverOutcome.TIMEOUT;
				timeSpent = watch.elapsedTime();
				return;
			}

			Vertex p = fringe.removeSmallest();
			numOfDequeue ++;

			for (WeightedEdge<Vertex> edge : input.neighbors(p)) {
				relax(edge, fringe, input, end);
			}
		}

		timeSpent = watch.elapsedTime();
		outcome = SolverOutcome.UNSOLVABLE;
		solution.clear();
		solutionWeight = 0;


	}

  /* update better value. */
  private void relax(WeightedEdge<Vertex> e, ExtrinsicMinPQ<Vertex> fringe, AStarGraph<Vertex> graph, Vertex end) {
    // 		• p = e.from(), q = e.to(), w = e.weight()
    //		• if distTo[p] + w < distTo[q]:
    //
    //			• distTo[q] = distTo[p] + w
    //			• if q is in the PQ: changePriority(q, distTo[q] + h(q, goal))
    // if q is not in PQ: add(q, distTo[q] + h(q, goal))
	  Vertex p = e.from();
	  Vertex q = e.to();
	  double w = e.weight();
	  if (!distTo.containsKey(q) || distTo.get(p) + w < distTo.get(q)) {
	      distTo.put(q, distTo.get(p) + w);
	      edgeTo.put(q, p);

		  if (fringe.contains(q)) {
			  fringe.changePriority(q, distTo.get(q) + graph.estimatedDistanceToGoal(q, end));
		  } else {
			  fringe.add(q, distTo.get(q) + graph.estimatedDistanceToGoal(q, end));
		  }
	  }




  }

	@Override
	public SolverOutcome outcome() {
		return this.outcome;
	}

	@Override
	public List<Vertex> solution() {
		return this.solution;
	}

	@Override
	public double solutionWeight() {
		return this.solutionWeight;
	}

	@Override
	public int numStatesExplored() {
		return numOfDequeue;
	}

	@Override
	public double explorationTime() {
		return timeSpent;
	}
}
