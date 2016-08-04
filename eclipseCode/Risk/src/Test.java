import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
//		File in = new File("res/F.in");
		File in = new File("res/example.in");
		Scanner scan = new Scanner(in);

		int numCase = scan.nextInt();
		int numR = 0;
		int[] ArmyArr = null;
		int[][] map = null;
		// manage input for each case
		for (int i = 0; i < numCase; i++) {
			// get number of regions
			numR = scan.nextInt();

			// get number of armies on each region
			ArmyArr = new int[numR];
			for (int j = 0; j < numR; j++) {
				ArmyArr[j] = scan.nextInt();
			}

			// get connectivities
			map = new int[numR][numR];
			for (int row = 0; row < numR; row++) {
				String line = scan.next();
				for (int col = 0; col < numR; col++) {
					if (line.charAt(col) == 'Y') {
						map[row][col] = 1;
					} else {
						map[row][col] = 0;
					}
				}
			}
//			System.out.println("map: " + Arrays.deepToString(map));

			// use enemy region index from ArmyArr
			// find regions which are connected with the enemy regions
			boolean[] isConnected = new boolean[numR];
			for (int k = 0; k < numR; k++) {
				if (ArmyArr[k] == 0) {
					for (int x = 0; x < numR; x++) {
						if (map[k][x] == 1) {
							isConnected[x] = true;
						}
					} // End inner for
				}
			} // End outer for
			System.out.println("isConnected" + Arrays.toString(isConnected));

			// find the weakest regions
			int[] weak = new int[numR];
			int min = Integer.MAX_VALUE;
			for (int n = 0; n < numR; n++) {
				if (min > ArmyArr[n] && ArmyArr[n] != 0) {
					min = ArmyArr[n];
				}
			}
			for (int n = 0; n < numR; n++) {
				if (ArmyArr[n] == min) {
					weak[n] = min;
				}
			}
			System.out.println("weak: " + Arrays.toString(weak));
			System.out.println("min: " + min);

			// for each weakest region,
			// find armies from another region,
			// which can transfer the most number of armies.
			int max = Integer.MIN_VALUE;
			for (int w = 0; w < numR; w++) {
				if(weak[w]==min){
					for(int p = 0; p < numR; p++){
						if(map[w][p]==1 && max < ArmyArr[p]){
							max = ArmyArr[p];
						}
					}
				}
			}
			
			System.out.println("max: " + max);
			int answer = 0;
			if(max == 0){
				answer = min;
			}else{
				answer = max -1 + min;
			}
			
			System.out.println(answer);
			
		} // End for loop
		scan.close();
	}
}
