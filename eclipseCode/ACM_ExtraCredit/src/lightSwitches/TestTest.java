package lightSwitches;

import java.util.ArrayList;
import java.util.Scanner;

public class TestTest {

	private static long count = 0l;
	private static long linecount = 0l;

	public static void main(String[] args) {
		long numBulb = 12345678912345678l;
		long time = 103406789000008l;
		long theBulb = 12345678912345608l;

		time = time % numBulb;
		ArrayList<Long> result = new ArrayList<>();
		result = findNumPrimeFactors(theBulb);
		System.out.println("result: " + result.toString());
//		sim(numBulb, time, theBulb);

//		System.out.println(count);

	}

	private static void sim(long numBulb, long time, long theBulb) {
		//while time < (theBulb / 2) + 1,
		//calculate the product of each combination of prime factors of "theBulb"
		//if products < (theBulb / 2) + 1, count ++
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

}
