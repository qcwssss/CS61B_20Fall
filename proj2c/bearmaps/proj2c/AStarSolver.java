package bearmaps.proj2c;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{
	private SolverOutcome outcome;
	private double solutionWeight;
	private List<Vertex> solution;
	private double timeSpent;

	public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
		Stopwatch watch = new Stopwatch();
		ExtrinsicMinPQ<Vertex> fringe = new ArrayHeapMinPQ<>();
		// Create a PQ where each vertex v will have priority p equal to the
		// sum of v’s distance from the source plus the heuristic estimate from v to the goal.
		Vertex source;
		double priorStart = input.estimatedDistanceToGoal(start, end) + 0;
		fringe.add(start, priorStart);
		while (fringe.size() != 0 || fringe.getSmallest() == end || outcome() == SolverOutcome.TIMEOUT) {
			Vertex p = fringe.getSmallest();
			relax(new WeightedEdge<>());
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
		return 0;
	}
}
