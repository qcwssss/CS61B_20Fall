//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package bearmaps.proj2c.streetmap.alg4;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public final class StdOut {
	private static final String CHARSET_NAME = "UTF-8";
	private static final Locale LOCALE;
	private static PrintWriter out;

	private StdOut() {
	}

	public static void close() {
		out.close();
	}

	public static void println() {
		out.println();
	}

	public static void println(Object x) {
		out.println(x);
	}

	public static void println(boolean x) {
		out.println(x);
	}

	public static void println(char x) {
		out.println(x);
	}

	public static void println(double x) {
		out.println(x);
	}

	public static void println(float x) {
		out.println(x);
	}

	public static void println(int x) {
		out.println(x);
	}

	public static void println(long x) {
		out.println(x);
	}

	public static void println(short x) {
		out.println(x);
	}

	public static void println(byte x) {
		out.println(x);
	}

	public static void print() {
		out.flush();
	}

	public static void print(Object x) {
		out.print(x);
		out.flush();
	}

	public static void print(boolean x) {
		out.print(x);
		out.flush();
	}

	public static void print(char x) {
		out.print(x);
		out.flush();
	}

	public static void print(double x) {
		out.print(x);
		out.flush();
	}

	public static void print(float x) {
		out.print(x);
		out.flush();
	}

	public static void print(int x) {
		out.print(x);
		out.flush();
	}

	public static void print(long x) {
		out.print(x);
		out.flush();
	}

	public static void print(short x) {
		out.print(x);
		out.flush();
	}

	public static void print(byte x) {
		out.print(x);
		out.flush();
	}

	public static void printf(String format, Object... args) {
		out.printf(LOCALE, format, args);
		out.flush();
	}

	public static void printf(Locale locale, String format, Object... args) {
		out.printf(locale, format, args);
		out.flush();
	}

	public static void main(String[] args) {
		println("Test");
		println((int)17);
		println(true);
		printf("%.6f\n", 0.14285714285714285D);
	}

	static {
		LOCALE = Locale.US;

		try {
			out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), true);
		} catch (UnsupportedEncodingException var1) {
			System.out.println(var1);
		}

	}
}
