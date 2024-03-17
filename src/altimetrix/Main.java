package altimetrix;

public class Main {
	public static void main(String[] args) {
		int[] array = { 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0 };
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		while (leftIndex < rightIndex) {
			while (array[leftIndex] == 1 && leftIndex < rightIndex) {
				leftIndex++;
			}
			while (array[rightIndex] == 0 && leftIndex < rightIndex) {
				rightIndex--;
			}
			if (leftIndex < rightIndex) {
				swap(array, leftIndex, rightIndex);
				leftIndex++;
				rightIndex--;
			}	}
		// Print the arranged array
		System.out.print("{");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1) {
				System.out.print(", ");
			}	}
		System.out.println("}");

		// Print in zigzag manner
		/*
		 * int count = 0; System.out.println(); for (int num : array) {
		 * System.out.print(num); count++; if (count == 2) { System.out.print(" ");
		 * count = 0; count--; System.out.println(); } }
		 */	}
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
