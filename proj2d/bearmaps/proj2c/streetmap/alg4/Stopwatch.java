//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package bearmaps.proj2c.streetmap.alg4;

import edu.princeton.cs.algs4.StdOut;

public class Stopwatch {
	private final long start = System.currentTimeMillis();

	public Stopwatch() {
	}

	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (double)(now - this.start) / 1000.0D;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Stopwatch timer1 = new Stopwatch();
		double sum1 = 0.0D;

		for(int i = 1; i <= n; ++i) {
			sum1 += Math.sqrt((double)i);
		}

		double time1 = timer1.elapsedTime();
		StdOut.printf("%e (%.2f seconds)\n", new Object[]{sum1, time1});
		Stopwatch timer2 = new Stopwatch();
		double sum2 = 0.0D;

		for(int i = 1; i <= n; ++i) {
			sum2 += Math.pow((double)i, 0.5D);
		}

		double time2 = timer2.elapsedTime();
		StdOut.printf("%e (%.2f seconds)\n", new Object[]{sum2, time2});
	}
}
