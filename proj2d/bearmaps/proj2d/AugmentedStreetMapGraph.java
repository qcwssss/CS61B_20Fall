package bearmaps.proj2d;

import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import bearmaps.proj2c.streetmap.StreetMapGraph;
import bearmaps.proj2c.streetmap.Node;
import bearmaps.proj2d.trie.MyTrieSet;
import bearmaps.proj2d.trie.TrieSet61B;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, Chen Qiu
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private final KDTree kdT;
    private final HashMap<Point, Node> pointToNode;
    private final HashMap<String, List<Node>> nameToNode;
    private final HashMap<String, String> cleanToFullName;
    private final TrieSet61B trie;


    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();
        this.pointToNode = new HashMap<>();
        // Implement autocomplete
        nameToNode = new HashMap<>();
        trie = new MyTrieSet();
        cleanToFullName = new HashMap<>();

        ArrayList<Point> listOfPoints = new ArrayList<>(nodes.size());
        for (Node n : nodes) {
            if (neighbors(n.id()).size() > 0) {
                Point p = new Point(n.lon(), n.lat());
                pointToNode.put(p, n);
                listOfPoints.add(p);
            }

            if (n.name() != null) {
                String cleanName = cleanString(n.name()), fullName = n.name();
                cleanToFullName.put(cleanName, fullName);
                this.trie.add(cleanName);

                // multiple locations can share the same name
                if (!nameToNode.containsKey(cleanName)) {
                    nameToNode.put(cleanName, new LinkedList<>());
                }
                nameToNode.get(cleanName).add(n);

            }
        }

        this.kdT = new KDTree(listOfPoints);

    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point closePoint = this.kdT.nearest(lon, lat);
        Node closeNode = this.pointToNode.get(closePoint);
        return closeNode.id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        prefix = cleanString(prefix);
        List<String> cleanNameList =  trie.keysWithPrefix(prefix);
        List<String> result = new LinkedList<>();
        for (String clean : cleanNameList) {
            result.add(this.cleanToFullName.get(clean));
        }
        return result;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String cleanLocation = cleanString(locationName);
        List<Map<String, Object>> listOfNodes = new LinkedList<>();

        if (!cleanToFullName.containsKey(cleanLocation)) {
            return null;
        }
        for (Node n : nameToNode.get(cleanLocation)) {
            Map<String, Object> locationMap = new HashMap<>();
            // {"lat", "lon", "name", "id" };
            locationMap.put("lat", n.lat());
            locationMap.put("lon", n.lon());
            locationMap.put("name", n.name());
            locationMap.put("id", n.id());

            listOfNodes.add(locationMap);
        }

        return listOfNodes;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    // Does cleanString method remove the spaces?
    public static void main(String[] args) {
        String day = "Claremont Day";
        String expected = "claremont day";
        System.out.println("Does cleanString method remove the spaces?\n"
                + day + " => "+ cleanString(day) + "\n" +
                expected.equals(cleanString(day)));


    }

}
