import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// do dp algo, get answer to mx array
		int max = 32;
		int maxStep = 14;
		// algo step 1: init int array
		int[][][] mx = new int[15][max][max];

		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				mx[0][x][y] = 0;
			}
		}
		mx[0][15][15] = 1;

		// algo step 2: simulate steps from 1 to 14
		for (int step = 1; step <= maxStep; step++) {
			for (int x = 1; x < max - 1; x++) {
				for (int y = 1; y < max - 1; y++) {
					mx[step][x][y] = mx[step - 1][x - 1][y - 1] + mx[step - 1][x - 1][y] + mx[step - 1][x][y + 1]
							+ mx[step - 1][x + 1][y + 1] + mx[step - 1][x + 1][y] + mx[step - 1][x][y - 1];
				}
			}
		}

		// scan user input
		Scanner scan = new Scanner(System.in);
		int numCase = scan.nextInt();

		for (int i = 0; i < numCase; i++) {
			// print answer from answer array
			System.out.println(mx[scan.nextInt()][15][15]);
		}
		scan.close();

	}

}