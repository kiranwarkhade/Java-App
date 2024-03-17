package altimetrix;

public class ZigZagArray {
	public static void main(String[] args) {
		int[] array = { 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0 };
		arrangeZigZag(array);
		// Print the arranged array in zigzag manner
		printZigZag(array);
	}

	private static void arrangeZigZag(int[] array) {
		int left = 0;
		int right = array.length - 1;

		while (left < right) {
			while (left < right && array[left] == 1) {
				left++;
			}
			while (left < right && array[right] == 0) {
				right--;
			}

			if (left < right) {
				// Swap 1 and 0
				int temp = array[left];
				array[left] = array[right];
				array[right] = temp;

				left++;
				right--;
			}
		}
	}

	private static void printZigZag(int[] array) {
		int numRows = (int) Math.ceil((double) array.length / 5);

		for (int i = 0; i < numRows; i++) {
			int startIdx = i * 5;
			int endIdx = Math.min(startIdx + 5, array.length);

			for (int j = startIdx; j < endIdx; j++) {
				System.out.print(array[j] + " ");
			}
		}
	}
}






/**
 * java 8
 * 
 * import java.util.Arrays;
 * 
 * public class ZigZagArray { public static void main(String[] args) { int[]
 * array = {0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0};
 * 
 * // Arrange all the 1s to the left and 0s to the right array =
 * arrangeArray(array);
 * 
 * // Print the arranged array in zigzag manner printZigZag(array); }
 * 
 * private static int[] arrangeArray(int[] array) { int[] arrangedArray =
 * Arrays.stream(array) .boxed() .sorted((a, b) -> Integer.compare(b, a))
 * .mapToInt(Integer::intValue) .toArray();
 * 
 * return arrangedArray; }
 * 
 * private static void printZigZag(int[] array) { int numRows = (int)
 * Math.ceil((double) array.length / 5);
 * 
 * for (int i = 0; i < numRows; i++) { int startIdx = i * 5; int endIdx =
 * Math.min(startIdx + 5, array.length);
 * 
 * for (int j = startIdx; j < endIdx; j++) { System.out.print(array[j] + " "); }
 * System.out.println(); } } }
 */
