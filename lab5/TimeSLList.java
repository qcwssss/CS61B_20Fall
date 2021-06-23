import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int exponent = 8;// constant
        int thousand = 1000;

        Stopwatch sw = new Stopwatch();
        SLList<Integer> dataSize = new SLList<>();
        SLList<Double> time = new SLList<>();

        // add different size of the data structure in a list
        for (int i = 0; i < exponent; i++) {
            dataSize.addLast(thousand * (int)Math.pow(2, i));
        }
        System.out.println("Timing table for addLast(SLList)");
        // test addLast for SLList
        SLList<Integer> testAddLast = new SLList<>(); // arbitrarily choose Integer
        for (Integer num : dataSize) {
            for (int i = 0; i < num; i++) {
                testAddLast.addLast(num);
            }
            time.addLast(sw.elapsedTime());
        }
        //for (Double second : time) { System.out.println(second);}
        printTimingTable(dataSize, time, dataSize);

    }

}
