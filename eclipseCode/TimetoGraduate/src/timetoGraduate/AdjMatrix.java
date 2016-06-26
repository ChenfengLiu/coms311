package timetoGraduate;

import java.util.ArrayList;

public class AdjMatrix {
	/**
	 * x - adjacency matrix, weighted, and directed graph
	 */
	private int[][] x;
	/**
	 * v - number of vertices
	 */
	private int v;

	/**
	 * A constructor that sets up the matrix using rule() method.
	 * @param courseList - given a list of course from user input
	 * @param numClass - given the number of required course
	 */
	public AdjMatrix(ArrayList<Course> courseList, int numClass) {
		this.v = numClass;
		x = new int[v][v];

		for (int row = 0; row < v; row++) {
			for (int col = 0; col < v; col++) {
				x[row][col]= rule(courseList.get(row),courseList.get(col));
			}
		}
	}
	/**
	 * Calculate value of the edge from course A to course B by rules.
	 * Rule 1: F to S, or S to F, Edge += 1
	 * @param A - 
	 * @param B-
	 * @return
	 */
	private int rule(Course A, Course B){
		return 0;
	}
	private int checkPrerequest(Course check){
		return 0;
	}

}
