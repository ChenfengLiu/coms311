package rumors;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		File in = new File("res/a.in");
//		File in = new File("res/example.in");
		Scanner scan = new Scanner(in);
//		Scanner scan = new Scanner(System.in);

		int n = 0;
		int[][] mx;

		while (scan.hasNextLine()) {
			// Step1. Get n value, init n*n matrix
			n = scan.nextInt();
			if (n == 0) {
				scan.close();
				return;
			}
			mx = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) {
						mx[i][j] = 0;
					} else {
						mx[i][j] = 9999;
					}
				}
			}

			// Step2. construct matrix
			for (int i = 0; i < n; i++) {
				int contact = scan.nextInt();
				for (int j = 0; j < contact; j++) {
					int numPerson = scan.nextInt();
					int time = scan.nextInt();
					mx[i][numPerson - 1] = time;
				}
			}

			// Step3. use floyd warshall algorithm
			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						mx[i][j] = Math.min(mx[i][j], mx[i][k] + mx[k][j]);
					}
				}
			}
			System.out.println(Arrays.deepToString(mx));

			// Step5. find result: return min of max of each row
			int numPerson = -1, rowMin = 9999;

			// create timeMax, init timeMax
			int[] timeMax = new int[n];
			for (int i = 0; i < n; i++) {
				timeMax[i] = -1;
			}
			// calculate timeMax, rowMin, and numPerson
			for (int row = 0; row < n; row++) {
				for (int col = 0; col < n; col++) {
					timeMax[row] = Math.max(mx[row][col], timeMax[row]);
				}
				if (rowMin > timeMax[row]) {
					rowMin = timeMax[row];
					numPerson = row;
				}
			}

			if (rowMin == 9999) {
				System.out.println("disjoint");
			} else {
				String answer = (numPerson + 1) + " " + rowMin;
				System.out.println(answer);
			}
		}
		scan.close();

	}

}
