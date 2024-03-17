package altimetrix;

import java.util.Arrays;
import java.util.Comparator;

public class CaseSpecificSorting {
    public static void main(String[] args) {
        String input = "srbDKi";

        // Convert the string to an array of characters
        char[] charArray = input.toCharArray();

        // Case-specific sorting using a custom comparator
        Arrays.sort(charArray);

        // Convert the sorted array back to a string
        String output = new String(charArray);

        // Display the sorted string
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
    }
}

class CaseSpecificComparator implements Comparator<Character> {
    @Override
    public int compare(Character char1, Character char2) {
        // Uppercase letters come first, then lowercase letters
        return Character.compare(Character.toLowerCase(char1), Character.toLowerCase(char2));
    }
}
