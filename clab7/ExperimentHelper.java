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
        int result = 0;
        int i = 0;
        while (i < Math.log(N) ) {
            result += (Math.pow(2, i) * i);
            i++;
        }
        //if N is not a power of 2 ?
        if (Math.pow(2,i) > N) {
            result += i*(N - Math.pow(2, i-1));
        }
        return result;
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
        return 0;
    }
}
