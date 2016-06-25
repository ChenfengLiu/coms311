package stringsWithSameLetters;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * An ACM question: Strings with Same Letters. This program will take user
 * inputs from system.in line by line as Strings. Then, calculate whether each
 * pair of input contains the exact same letters. The result will be printed
 * after the users have done with the input. For more details about this
 * question, please view Question # H in extraCreditProblems.pdf
 * 
 * @author chenfeng
 * 
 * 
 */
public class StringsWithSameLetters {

	public static void main(String[] args) {

		ArrayList<String> arrList = new ArrayList<>();
		// Obtain input data from the user
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter you input down below: ");
		while (scan.hasNextLine()) {
			String a = scan.nextLine();
			String b = scan.nextLine();
			if (a.equals("END") && b.equals("END")) {
				break;
			}
			arrList.add(a);
			arrList.add(b);
		}
		scan.close();

		// find match for each pair of Strings
		for (int i = 0; i < arrList.size() / 2; i++) {
			System.out.print("Case " + (i + 1) + ": ");
			if (findMatch(arrList.get(2 * i), arrList.get(2 * i + 1))) {
				System.out.println("same");
			} else {
				System.out.println("different");
			}
		}

	}

	/**
	 * Find whether a string "a" has the exact same chars as that of string "b"
	 * or not.
	 * 
	 * @param a
	 *            - string a
	 * @param b
	 *            - string b
	 * @return - true if string "a" has the exact same chars as that of string
	 *         "b". - false otherwise.
	 */
	public static boolean findMatch(String a, String b) {
		// if String length does not match, return false
		if (a.length() != b.length()) {
			return false;
		} else {
			// for each char in String "a":
			for (int i = 0; i < a.length(); i++) {
				boolean isMatchChar = false;
				// j is always bigger or equal to i
				for (int j = i; j < a.length(); j++) {
					isMatchChar = false;
					// if two chars match, do swap for String "b"
					if (a.charAt(i) == b.charAt(j)) {
						isMatchChar = true;
						b = swap(b, i, j);
						break;
					}
				} // End inner for
					// if no char in String "b" matches the char in String "a",
					// return false
				if (!isMatchChar) {
					return false;
				}
			} // End outer for
			return true;
		}

	}

	/**
	 * Swap the two chars of a string "a", at "indexA" and "indexB". NOTE:
	 * "indexA" is always less or equal to "indexB".
	 * 
	 * @param a
	 *            - a string needs to be swapped.
	 * @param indexA
	 *            - the first char at indexA
	 * @param indexB
	 *            - the second char at indexB
	 */
	public static String swap(String a, int indexA, int indexB) {
		// swap is unnecessary when indexA == indexB
		if (indexA == indexB)
			return a;
		// cut the string into 3 parts not including the two chars.
		String first = a.substring(0, indexA);
		String second = a.substring(indexA + 1, indexB);
		String third = a.substring(indexB + 1, a.length());

		// rearrange the components of the string (flip the two chars).
		return first + a.charAt(indexB) + second + a.charAt(indexA) + third;
	}
}
