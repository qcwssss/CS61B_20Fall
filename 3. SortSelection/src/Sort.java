public class Sort {

	public static void sort(String[] arrayOfString) {
		// find the smallest item.
		// move it to the front, swap it
		// keep sort for the rest of the array
		int smallestIdx = findSmallest(arrayOfString);
		swap(arrayOfString, 0, smallestIdx);
	}

	/**
	 * Sort recursively.
	 *
	 * @param array an array of string.
	 * @param start the starting index of an unsorted array.
	 */
	private static void sort(String[] array, int start) {
		
		int smallestIdx = findSmallest(array);
		swap(array,0, smallestIdx);

		sort(array, start + 1);
	}

	/**
	 * Return the smallest string in array x.
	 *
	 * @param x the string will be sorted.
	 * @return the smallest.
	 */
	public static int findSmallest(String[] x) {
		int smallestIndex = 0;
		for (int i = 0; i < x.length; i++) {
			int compare = x[smallestIndex].compareTo(x[i]);
			if (compare > 0) {
				smallestIndex = i;
			}
		}
		return smallestIndex;
	}

	/**
	 * swap two items.
	 *
	 * @param x array.
	 * @param a the index of item will be swapped.
	 * @param b the index of item will be swapped.
	 */
	public static void swap(String[] x, int a, int b) {
		String temp = x[a];
		x[a] = x[b];
		x[b] = temp;
	}
}
