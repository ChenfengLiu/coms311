package timetoGraduate;

import java.util.Arrays;

public class Test {
	private static int count = 0;

	public static void main(String[] args) {
		// Course a = new Course(0, "F", 0, new int[0]);
		//
		// Course b = new Course(1, "S", 1, new int[] { 0 });
		// Course c = new Course(2, "B", 2, new int[] { 0, 1 });
		//
		// int semester = 2;
		// boolean[] finished = new boolean[3];
		// finished[0] = true;
		// finished[1] = true;
		// finished[2] = false;
		//
		// System.out.println(permission(a,semester,finished));

		int[] arr = { 0, 1, 2, 3, 4 };
		int k = 3;
		int numRows = combinationCal(arr.length, k);
		System.out.println("numRows: " + numRows);

		int[][] result = new int[numRows][k];
		result = combination(arr, k, 0, result);
		System.out.println(Arrays.deepToString(result));
	}

	public static boolean permission(Course c, int semester, boolean[] finished) {
		// check if already finished
		boolean isFinished = finished[c.getName()];
		// check semester
		boolean isTime = (c.getSemester() == semester % 2) || (c.getSemester() == 2);
		// check pre-request
		boolean isQualified = true;
		for (int i = 0; i < c.getNumPreRequest(); i++) {
			if (!finished[c.getPreRequest(i)]) {
				isQualified = false;
				break;
			}
		}
		System.out.println("isFinished: " + isFinished);
		System.out.println("isTime: " + isTime);
		System.out.println("isQualified: " + isQualified);
		if (!isFinished && isTime && isQualified) {
			return true;
		} else {
			return false;
		}
	}

	public static int[][] combination(int[] arr, int k, int from, int[][] result) {
		if (k == 0) {
			count = count + 1;

			return result;
		}
		for (int i = from; i <= arr.length - k; i++) {
			result[count][result[count].length - k] = arr[i];
			combination(arr, k - 1, i + 1, result);
		}
		return result;

	}

	/**
	 * Calculate the integer value of the combination function nCk (choose k
	 * from n)
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	private static int combinationCal(int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n - k));

	}

	/**
	 * Calculate the factorial of an integer
	 * 
	 * @param i
	 *            - a nonnegative integer
	 * @return - the factorial of integer "i"
	 */
	private static int factorial(int i) {
		if (i == 0) {
			return 1;
		} else {
			return i * factorial(i - 1);
		}

	}

}
