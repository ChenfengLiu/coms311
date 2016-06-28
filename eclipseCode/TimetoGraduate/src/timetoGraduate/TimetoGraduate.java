package timetoGraduate;

import java.util.ArrayList;
import java.util.Scanner;

public class TimetoGraduate {

	private static int count = 0;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		while (true) {

			int numClass, maxClass;
			System.out.println("Please enter you input below: ");
			numClass = scan.nextInt();
			maxClass = scan.nextInt();

			if (numClass == -1 && numClass == -1) {
				scan.close();
				break;
			}

			ArrayList<Course> classList = new ArrayList<>();
			String[] className = new String[numClass];

			// obtain all the courses' names
			for (int i = 0; i < numClass; i++) {
				className[i] = scan.next();
			}

			// obtain detail info of each course
			for (int i = 0; i < numClass; i++) {
				String Cname = scan.next();
				// find the course name in className, obtain index j, which is
				// the alternative course name
				for (int j = 0; j < numClass; j++) {
					if (className[j].equals(Cname)) {
						// set semester: F=0,S=1,B=2
						String semester = scan.next();
						// set number of pre-request
						int numPrerequest = scan.nextInt();
						// set pre-request int[]
						int[] preRequest = new int[numPrerequest];

						for (int k = 0; k < numPrerequest; k++) {
							String preName = scan.next();
							// find the course name in className, obtain index
							// p, which is the alternative course name
							for (int p = 0; p < numClass; p++) {
								if (className[p].equals(preName)) {
									preRequest[k] = p;
								}
							} // End for #4

						} // End for #3

						// Add course to array list
						Course c = new Course(j, semester, numPrerequest, preRequest);
						classList.add(c);
					} // End if

				} // End for #2
			} // End for #1

			/*-------------------------------------------------------------------------------------------------*/
			/*End of User Input. Start simulation.-------------------------------------------------------------*/
			/*-------------------------------------------------------------------------------------------------*/

			//given an arrayList of n course objects, find time to graduate
			boolean[] finished = new boolean[numClass];
			// initialize boolean array: finished
			for (int i = 0; i < numClass; i++) {
				finished[i] = false;
			}
			count = 0;
			min = Integer.MAX_VALUE;
			int semester = 0;
			//Start simulation.
			nextSemester(classList, finished, semester, maxClass);
			System.out.println("The minimum number of semesters required to graduate is " + min);

		} // End while loop

		scan.close();

	}

	/*---------------------------------------------------------------------------------------------------------*/
	/*Private Helper Methods-----------------------------------------------------------------------------------*/
	/*---------------------------------------------------------------------------------------------------------*/
	
	/**
	 * The recursive method simulates all the possibilities then give the
	 * minimum semester after done the simulation.
	 * 
	 * @param courseList
	 *            - all required courses
	 * @param finished
	 *            - a boolean array indicate if a class is finished or not.
	 * @param semester
	 *            - current semester
	 * @param maxClass
	 *            - maximum number of class which can be taken in one semester
	 */
	private static void nextSemester(ArrayList<Course> courseList, boolean[] finished, int semester, int maxClass) {
		if (isDone(finished)) {
			if (semester < min)
				min = semester;
		} else {
			ArrayList<Course> candidateList = findCandidates(courseList, semester, finished);
			// Store all candidates' name in an integer array
			int[] candidates = new int[candidateList.size()];
			for (int i = 0; i < candidateList.size(); i++) {
				candidates[i] = candidateList.get(i).getName();
			}
			// if # of candidates is less than max courses can take, finish
			// those courses.
			if (candidateList.size() <= maxClass) {
				for (int i = 0; i < candidateList.size(); i++) {
					finished[candidateList.get(i).getName()] = true;
				}
				semester = semester + 1;
				nextSemester(courseList, finished, semester, maxClass);
			} else {
				int rows = combinationCal(candidateList.size(), maxClass);
				int[][] allCombination = new int[rows][maxClass];
				int[] oneCombination = new int[maxClass];
				allCombination = combination(candidates, maxClass, 0, oneCombination, allCombination);
				// for each combination, simulate next semester.
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < maxClass; j++) {
						finished[allCombination[i][j]] = true;
					}
					semester = semester + 1;
					nextSemester(courseList, finished, semester, maxClass);
				}
			}
		}
	}

	/**
	 * The method isDone checks if all the courses have been finished.
	 * 
	 * @param finished
	 *            - a boolean array indicates if a course has been done.
	 * @return - true if all the courses have been finished, false otherwise.
	 */
	private static boolean isDone(boolean[] finished) {
		for (int i = 0; i < finished.length; i++) {
			if (finished[i] == false)
				return false;
		}
		return true;
	}

	/**
	 * The method findCandidates finds all possible candidate courses which can
	 * be taken.
	 * 
	 * @param courseList
	 *            - all required courses
	 * @param semester
	 *            - current semester
	 * @param finished
	 *            - a boolean array indicate if a class is finished or not.
	 * @return - an array list of all candidates.
	 */
	private static ArrayList<Course> findCandidates(ArrayList<Course> courseList, int semester, boolean[] finished) {
		ArrayList<Course> candidateList = new ArrayList<>();
		for (Course c : courseList) {
			if (permission(c, semester, finished)) {
				candidateList.add(c);
			}
		}
		return candidateList;
	}
	
	/**
	 * The permission method returns true if the course is valid to take in this
	 * semester. The course has to meet all the pre-requests and also has to be
	 * unfinished.
	 * 
	 * @param c
	 *            - The course going to be evaluated.
	 * @param semester
	 *            - current semester: even = "F", odd = "S"
	 * @param finished
	 *            - an boolean array of finished courses
	 * @return - returns true if the course can be taken, otherwise false.
	 */
	private static boolean permission(Course c, int semester, boolean[] finished) {
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
		if (!isFinished && isTime && isQualified) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The combination function returns a 2D integer array of all combinations
	 * of candidate courses.
	 * 
	 * @param arr
	 *            - an integer array that stores all possible candidate courses.
	 * @param k
	 *            - combinations of size k
	 * @param from
	 *            - the starting position of the cursor of int[] arr
	 * @param result
	 *            - a integer array stores one possible combination of courses.
	 * @param finalResult
	 *            - a 2D integer array stores all combinations of candidate
	 *            courses.
	 * @return - returns finalResul as the 2D integer array containing all
	 *         combinations of candidate courses.
	 */
	private static int[][] combination(int[] arr, int k, int from, int[] result, int[][] finalResult) {
		if (k == 0) {
			for (int i = 0; i < result.length; i++) {
				finalResult[count][i] = result[i];
			}
			count++;
			return finalResult;
		}
		for (int i = from; i <= arr.length - k; i++) {
			result[result.length - k] = arr[i];
			combination(arr, k - 1, i + 1, result, finalResult);
		}
		return finalResult;

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
	 *            - a non-negative integer
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
