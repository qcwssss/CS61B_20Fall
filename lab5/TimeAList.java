/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
	    // constant
	    int exponent = 8;
	    int thousand = 1000;

        Stopwatch sw = new Stopwatch();
        AList<Integer> dataSize = new AList<>();
        AList<Double> time = new AList<>();

        // calculate time while in loop
        for (int i = 0; i < exponent; i++) {
            dataSize.addLast(thousand * (int)Math.pow(2, i));
        }
        System.out.println("Timing table for addLast");

        // Apply Iterator for enhanced for loop
        for (Integer num : dataSize) {
            // test addLast
            AList<Integer> timeTest = new AList<>();
            for (int i = 0; i < num; i++) {
                timeTest.addLast(num);
            }
            time.addLast(sw.elapsedTime());
        }
        // test whether time AList is added correctly
        //for (Double second : time) { System.out.println(second);}
        printTimingTable(dataSize, time, dataSize);

    }


}
