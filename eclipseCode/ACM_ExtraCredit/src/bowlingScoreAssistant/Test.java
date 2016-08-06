package bowlingScoreAssistant;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
	private static int caseCount = 0;

	public static void main(String[] args) {
		String test = "1 4 6 6 2 4 2 8 1 10 10 7 2 9 0";
		Scanner scan = new Scanner(test);

		while (scan.hasNext()) {
			// obtain user input
			int opScore = scan.nextInt();
			if (opScore == -1) {
				break;
			}
			int[] myScore = new int[16];
			int last = 16;
			for (int i = 0; i < last; i++) {
				myScore[i] = scan.nextInt();
				if (myScore[i] == 10) {
					last--;
				}
			}
			System.out.println("myScore arr: " + Arrays.toString(myScore));
			System.out.println("last: " + last);
			// calculate my score

			int score = 0;
			for (int i = 0; i < last; i++) {
				score += myScore[i];
			}
			for (int i = 0; i < last; i++) {
				if(myScore[i] == 10){
					
				}
			}
			// get score need to win.

		}
		scan.close();
	}

	private static int scoreCal(int[] myScore) {

		return 0;
	}

}
