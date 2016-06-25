package timetoGraduate;

public class ClassObj {
	private String name;
	private char semester;
	private int numPreRequest;
	private String[] preRequest;

	public ClassObj(String name, char semester, int numPreRequest, String[] preRequest) {
		this.setName(name);
		this.setSemester(semester);
		this.setNumPreRequest(numPreRequest);
		preRequest = new String[numPreRequest];
		for (int i = 0; i < numPreRequest; i++) {
			this.preRequest[i] = preRequest[i];
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSemester() {
		return semester;
	}

	public void setSemester(char semester) {
		this.semester = semester;
	}

	public int getNumPreRequest() {
		return numPreRequest;
	}

	public void setNumPreRequest(int numPreRequest) {
		this.numPreRequest = numPreRequest;
	}

	public void setPreRequestion(String course, int index) {
		preRequest[index] = course;
	}

	public void setPreRequestionArr(String[] courses, int numCourses) {
		preRequest = new String[numCourses];
		for (int i = 0; i < numCourses; i++) {
			preRequest[i] = courses[i];
		}
	}

	public String getPreRequest(int index) {
		return preRequest[index];
	}

	public String[] getPreRequestArr() {
		return preRequest;
	}

}
