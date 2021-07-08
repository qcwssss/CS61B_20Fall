/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        // the sum of the lengths of the paths to every node.
        int ipl = 0;
        int depthMax = (int) Math.log(N);
        int i; // the number of node
        for(i = 0; i < N; i++) {
            if (i != 0) {
                ipl += Math.log(i) / Math.log(2); // log is in base 10
            }
        }
        ipl += Math.log(i) / Math.log(2);
        return ipl;

    }


    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;
    }
}
