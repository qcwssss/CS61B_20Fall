package bearmaps.proj2c.slidingpuzzle;

import bearmaps.proj2c.AStarSolver;
import bearmaps.proj2c.LazySolver;
import bearmaps.proj2c.ShortestPathsSolver;
import bearmaps.proj2c.SolutionPrinter;

/**
 * Showcases how the AStarSolver can be used for solving sliding puzzles.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoSlidingPuzzleSolution {

    public static void main(String[] args) {
        Board start = Board.readBoard("BasicPuzzle4.txt");
        System.out.println(start);
        int N = start.size();
        Board goal = Board.solved(N);

        BoardGraph spg = new BoardGraph();

        ShortestPathsSolver<Board> solver = new AStarSolver<>(spg, start, goal, 20);
        SolutionPrinter.summarizeSolution(solver, "\n");
    }
}
