package centroidOfPointMasses;

import java.util.Scanner;

/**
 * This program calculates the centroid (x,y) of a set of point masses.For more
 * details about this question, please view Question # G in
 * extraCreditProblems.pdf
 * 
 * @author chenfeng
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] x, y, m;
		int n = 0;
		int counter = 0; // to count num of cases

		// scan user input
		while (scan.hasNextLine()) {
			n = scan.nextInt();
			if (n < 0)
				break;
			x = new int[n];
			y = new int[n];
			m = new int[n];
			for (int i = 0; i < n; i++) {
				x[i] = scan.nextInt();
				y[i] = scan.nextInt();
				m[i] = scan.nextInt();
			}
			double centroid_x = centroidCal(x, m, n);
			double centroid_y = centroidCal(y, m, n);
			counter = counter + 1;

			System.out.print("Case " + counter + ": ");
			System.out.printf("%.2f %.2f", centroid_x, centroid_y);
			System.out.println();
		}

		scan.close();

	}

	/**
	 * This helper method returns the result of sum(arr[i]*m[i]) / sum(m[i])
	 * 
	 * @param arr
	 *            - an integer array stores either x or y coordinates
	 * @param m
	 *            - an integer array stores masses
	 * @param n
	 *            - number of points
	 * @return - sum(arr[i]*m[i]) / sum(m[i])
	 */
	public static double centroidCal(int[] arr, int[] m, int n) {
		// sumArrM is the sum of arr[i]*m[i], sumM is the sum of m[i].
		int sumArrM = 0, sumM = 0;
		// calculate sum
		for (int i = 0; i < n; i++) {
			sumArrM += arr[i] * m[i];
			sumM += m[i];
		}
		return (double) sumArrM / sumM;
	}

}
