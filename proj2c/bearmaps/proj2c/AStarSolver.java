package bearmaps.proj2c;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{
	private SolverOutcome outcome;
	private double solutionWeight;
	private LinkedList<Vertex> solution;
	private double timeSpent;
	private int numOfDequeue;

	public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
		Stopwatch watch = new Stopwatch();

		this.solution = new LinkedList<>();
		solutionWeight = 0;
		numOfDequeue = 0;

		ExtrinsicMinPQ<Vertex> fringe = new ArrayHeapMinPQ<>();
		Map<Vertex, Double> distTo = new HashMap<>();
		Map<Vertex, Vertex> edgeTo = new HashMap<>(); // <to, from>

		distTo.put(start, 0.0);
		fringe.add(start, input.estimatedDistanceToGoal(start, end));
		
		while (fringe.size() > 0) {
			// time out
			if (watch.elapsedTime() > timeout ) {
				outcome = SolverOutcome.TIMEOUT;
				timeSpent = watch.elapsedTime();
				return;
			}
			// solved
			if (fringe.getSmallest().equals(end)) {
				outcome = SolverOutcome.SOLVED;
				timeSpent = watch.elapsedTime();
				solutionWeight = distTo.get(end);

				Vertex ptr = fringe.getSmallest();

				solution.add(ptr); // last node
				while (!ptr.equals(start)) {
					ptr = edgeTo.get(ptr);
					solution.addFirst(ptr);
				}
				solution.addFirst(start);
				return;
			}

			Vertex p = fringe.removeSmallest();
			numOfDequeue ++;
			for (WeightedEdge w: input.neighbors(p)) {
				relax(w);
				// update edgeTo, distTo
				Vertex curFrom = p;
				Vertex curTo = (Vertex) w.to();

				distTo.put(curTo, w.weight());
				edgeTo.put(curTo, p); // <to, from>
				double wPrior = input.estimatedDistanceToGoal(curFrom, end) + distTo.get(curFrom);
				// if (wPrior < distTo.get(curFrom)) { // ?? }
				if (distTo.containsKey(curTo) ) {
					if (wPrior < distTo.get(curFrom)) {
						fringe.changePriority(curTo, wPrior);
					}
				} else {
					fringe.add(curTo, wPrior);
				}
			}
		}


	}

  /* update better value. */
  private void relax(WeightedEdge<Vertex> e) {
    // 		• p = e.from(), q = e.to(), w = e.weight()
    //		• if distTo[p] + w < distTo[q]:
    //
    //			• distTo[q] = distTo[p] + w
    //			• if q is in the PQ: changePriority(q, distTo[q] + h(q, goal))
    // if q is not in PQ: add(q, distTo[q] + h(q, goal))
	  Vertex p = e.from();
	  Vertex q = e.to();
	  double w = e.weight();


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
		return 0;
	}

	@Override
	public double explorationTime() {
		return timeSpent;
	}
}
