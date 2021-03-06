package lightSwitches;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static int count = 0;
	private static int toggleCount = 0;
	private static int numCase = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNextLong()) {
			long numBulb = scan.nextLong();
			long time = scan.nextLong();
			long theBulb = scan.nextLong();
			
			time = time % numBulb;

			numCase = numCase + 1;
			count = 0;
			toggleCount = 0;
			sim(numBulb, time, theBulb);

			if (toggleCount % 2 == 0) {
				System.out.println("Case " + numCase + ": On");
			} else {
				System.out.println("Case " + numCase + ": Off");
			}
		}
		scan.close();

	}

	private static void sim(long numBulb, long time, long theBulb) {
		if (time >= theBulb) {
			toggleCount++;
		}
		if (time == 0) {
			// just assign an odd number, so that the Bulb is OFF.
			toggleCount = 1;
			return;
		}
		if (time == 1) {
			// just assign an even number, so that the Bulb is OFF.
			toggleCount = 2;
			return;
		}
		long simTimeMax = Math.min(theBulb / 2, time);

		// get all prime factors, assign to longList
		ArrayList<Long> longList = findNumPrimeFactors(theBulb);

		// for all prime factors, choose i items.
		for (int i = 1; i <= longList.size(); i++) {
			// calculate num of possible combinations of choosing i items
			int numComb = combinationCal(longList.size(), i);
			long[] product = new long[i];
			// result array stores the product of each possible combination
			long[] result = new long[numComb];
			for (int j = 0; j < numComb; j++) {
				result[j] = 1; // init to 1
			}
			count = 0;
			result = productOfCombinations(longList, i, 0, product, result);

			// remove duplicates in result array
			long[] finalResult = new long[result.length];
			finalResult = removeDup(result);

			// Count toggle (num of product values which are positive and less
			// than simTimeMax)
			int a = 0;
			while (a < finalResult.length && finalResult[a] > 0) {
				if (finalResult[a] <= simTimeMax) {
					toggleCount++;
				}
				a++;
			} // End while loop

		} // End for loop

	}

	private static ArrayList<Long> findNumPrimeFactors(long theBulb) {
		ArrayList<Long> result = new ArrayList<>();
		while (theBulb % 2 == 0) {
			theBulb = theBulb / 2;
			result.add(2l);
		}
		for (long i = 3l; i <= Math.sqrt(theBulb); i = i + 2) {
			while (theBulb % i == 0) {
				theBulb = theBulb / i;
				result.add(i);
			}
		}
		if (theBulb > 2) {
			result.add(theBulb);
		}
		return result;
	}

	private static long[] productOfCombinations(ArrayList<Long> longList, int k, int from, long[] product,
			long[] result) {
		if (k == 0) {
			for (int i = 0; i < product.length; i++) {
				result[count] = result[count] * product[i];
			}

			count++;
			return result;
		}
		for (int i = from; i <= longList.size() - k; i++) {
			product[product.length - k] = longList.get(i);
			productOfCombinations(longList, k - 1, i + 1, product, result);
		}
		return result;

	}

	private static int combinationCal(int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	private static int factorial(int i) {
		if (i == 0) {
			return 1;
		} else {
			return i * factorial(i - 1);
		}

	}

	private static long[] removeDup(long[] result) {
		int counter = 0;
		boolean isDup = false;
		long[] finalResult = new long[result.length];
		for (int i = 0; i < result.length; i++) {
			isDup = false;
			for (int j = 0; j < result.length; j++) {
				if (result[i] == finalResult[j]) {
					isDup = true;
				}
			}
			if (!isDup) {
				finalResult[counter] = result[i];
				counter = counter + 1;
			}
		}
		return finalResult;
	}

}
