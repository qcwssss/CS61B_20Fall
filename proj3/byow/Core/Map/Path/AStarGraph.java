package byow.Core.Map.Path;

import java.util.List;

/**
 * Represents a graph of vertices.
 * Created by hug.
 */
public interface AStarGraph<Vertex> {
    List<WeightedEdge<Vertex>> neighbors(Vertex v);
    double estimatedDistanceToGoal(Vertex s, Vertex goal);
}
