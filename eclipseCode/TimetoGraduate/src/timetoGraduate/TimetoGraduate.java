package timetoGraduate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TimetoGraduate {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		while (true) {

			int numClass, maxClass;
			System.out.println("Please enter you input below: ");
			numClass = scan.nextInt();
			maxClass = scan.nextInt();
			System.out.println("numClass is: " + numClass);
			System.out.println("maxClass is: " + maxClass);

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
			System.out.println(Arrays.deepToString(className));

			// obtain detail info of each course
			for (int i = 0; i < numClass; i++) {
				String Cname = scan.next();
				// find the course name in className, obtain index j, which is
				// the alternative course name
				for (int j = 0; j < numClass; j++) {
					if (className[j].equals(Cname)) {
						// set semester: F=0,S=1,B=2
						String semester = scan.next();
						System.out.println("semester: " + semester);
						// set number of pre-request
						int numPrerequest = scan.nextInt();
						System.out.println("numPrerequest: " + numPrerequest);
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

			///////////////////////////////////////////////////////////////////////////////////////////////////////////
			for (int s = 0; s < numClass; s++) {
				System.out.print(classList.get(s).getName() + " " + classList.get(s).getSemester() + " "
						+ classList.get(s).getNumPreRequest() + " ");
				System.out.println(Arrays.toString(classList.get(s).getPreRequest()));
			} // End for #5

			// TODO: given an arrayList of n course objects, find time to
			// graduate
			System.out.println("Please wait till calculation is done.");
			boolean[] finished = new boolean[numClass];
			// initialize boolean array: finished
			for (int i = 0; i < numClass; i++) {
				finished[i] = false;
			}
			// TODO: create adjMatrix from given data (classList, numClass,
			// maxClass), then brute force
			System.out.println("calculation done.");

		} // End while loop

		scan.close();

	}

	public static boolean permission(Course c, int semester, boolean[] finished) {
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

	public static int[][] combination(int[] candidates, int maxClass) {
		int[][] result = new int[1][maxClass];
		return result;
	}

}
