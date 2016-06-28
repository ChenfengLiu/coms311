package timetoGraduate;

import java.util.ArrayList;

public class Test {
	private static int count = 0;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// Course a = new Course(0, "F", 0, new int[0]);
		// Course b = new Course(1, "S", 1, new int[] { 0 });
		// Course c = new Course(2, "B", 2, new int[] { 0, 1 });
		//
		// int semester = 2;
		// boolean[] finished = new boolean[3];
		// finished[0] = true;
		// finished[1] = true;
		// finished[2] = false;
		//
		// System.out.println(permission(a,semester,finished));

		// int[] arr = { 2, 3, 4 };
		// int k = 3;
		// int numRows = combinationCal(arr.length, k);
		// System.out.println("numRows: " + numRows);
		//
		// int[] answer = new int[k];
		// int[][] finalResult = new int[numRows][k];
		// finalResult = combination(arr, k, 0, answer, finalResult);
		// System.out.println(Arrays.deepToString(finalResult));
		//
		// boolean[] finished = { true, true, true };
		// System.out.println(isDone(finished));

		Course a = new Course(0, "F", 0, new int[0]);
		Course b = new Course(1, "S", 0, new int[0]);
		Course c = new Course(2, "S", 2, new int[] { 0, 1 });
		Course d = new Course(3, "B", 1, new int[] { 2 });
		ArrayList<Course> courseList = new ArrayList<>();
		courseList.add(a);
		courseList.add(b);
		courseList.add(c);
		courseList.add(d);
		boolean finished[] = { false, false, false, false };
		int semester = 0;
		int maxClass = 6;
		nextSemester(courseList, finished, semester, maxClass);
		System.out.println("The minimum number of semesters required to graduate is " + min);

	}

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
		// System.out.println("isFinished: " + isFinished);
		// System.out.println("isTime: " + isTime);
		// System.out.println("isQualified: " + isQualified);
		if (!isFinished && isTime && isQualified) {
			return true;
		} else {
			return false;
		}
	}

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
