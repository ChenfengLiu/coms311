package lightSwitches;

import java.util.ArrayList;

public class TestTest {

	private static int count = 0;
	private static int toggleCount = 0;

	public static void main(String[] args) {
		// long numBulb = 12345678912345678l;
		// long time = 103406789000008l;
		// long theBulb = 12345678912345608l;

		// long numBulb = 12345678912345678l;
		// long time = 1234567845678l;
		// long theBulb = 12345678912345608l;

		// long numBulb = 123456789123456l;
		// long time = 123456789121456l;
		// long theBulb = 123456781231456l;

		// long numBulb = 123456789123l;
		// long time = 123456789121l;
		// long theBulb = 123456781234l;

		// long numBulb = 123456789l;
		// long time = 0l;
		// long theBulb = 123456784l;

		// long numBulb = 1234567l;
		// long time = 1234563l;
		// long theBulb = 1234562l;

		// long numBulb = 1234567l;
		// long time = 0l;
		// long theBulb = 1234562l;

		// long numBulb = 3l;
		// long time = 1l;
		// long theBulb = 1l;

		// long numBulb = 20l;
		// long time = 10l;
		// long theBulb = 5l;

		 long numBulb = 55l;
		 long time = 10l;
		 long theBulb = 24l;

		time = time % numBulb;

		ArrayList<Long> longList = findNumPrimeFactors(theBulb);
		System.out.println("result: " + longList.toString());

		toggleCount = 0;
		sim(numBulb, time, theBulb);
		System.out.println("toggle : " + toggleCount);
		if (toggleCount % 2 == 0) {
			System.out.println("on");
		} else {
			System.out.println("off");
		}
		// System.out.println(count);
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
		System.out.println("sim time: " + simTimeMax);

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
