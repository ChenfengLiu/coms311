package lightSwitches;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * This program takes many sets of integers from system.in. Each set contains
 * three integers: # of light bulbs, the time since the bulbs are turned on, and
 * the bulb ID (number). Then the program will return if the light bulb with the
 * bulb ID (number) is "On" or "Off". For more details about this question,
 * please view Question # E in extraCreditProblems.pdf
 * 
 * @author chenfeng
 *
 */
public class LightSwitches {

	private static int count = 0;
	private static int numCase = 0;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		while (scan.hasNextLine()) {
			BigInteger numBulb = new BigInteger(scan.next());
			BigInteger time = new BigInteger(scan.next());
			BigInteger theBulb = new BigInteger(scan.next());
			time = time.remainder(numBulb);

			System.out.println("numBulb: " + numBulb);
			System.out.println("time: " + time);
			System.out.println("the bulb: " + theBulb);

			numCase = numCase + 1;
			count = 0;

			simulation(numBulb, time, theBulb);

			if (count % 2 != 0) {
				System.out.println("Case " + numCase + ": On");
			} else {
				System.out.println("Case " + numCase + ": Off");
			}
		}
		scan.close();

	}

	private static void simulation(BigInteger numBulb, BigInteger time, BigInteger theBulb) {
		BigInteger simTime = new BigInteger("1");
		BigInteger one = new BigInteger("1");
		while (simTime.compareTo(time) <= 0) {

			if (isExactDivision(theBulb, simTime)) {
				count = count + 1;
			}
			simTime = simTime.add(one);
		}
	}

	private static boolean isExactDivision(BigInteger n, BigInteger k) {
		BigInteger one = new BigInteger("1");
		if (n.remainder(k).compareTo(one) >= 0) {
			System.out.println("false");
			return false;
		} else {
			System.out.println("true");
			return true;
		}
	}

}
