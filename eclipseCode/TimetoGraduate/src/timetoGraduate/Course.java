package timetoGraduate;

public class Course {
	private int name, semester, numPreRequest;
	private int[] preRequest;

	public Course() {

	}

	public Course(int name, String semester, int numPreRequest, int[] preRequest) {

		this.name = name;

		if (semester.equals("F")) {
			this.semester = 0;
		} else if (semester.equals("S")) {
			this.semester = 1;
		} else {
			this.semester = 2;
		}
		this.numPreRequest = numPreRequest;
		this.preRequest = new int[numPreRequest];
		
		for (int i = 0; i < numPreRequest; i++) {
			this.preRequest[i] = preRequest[i];
		}

	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public void setSemester(String semester) {
		if (semester.equals("F")) {
			this.semester = 0;
		} else if (semester.equals("S")) {
			this.semester = 1;
		} else {
			this.semester = 2;
		}
	}

	public int getNumPreRequest() {
		return numPreRequest;
	}

	public void setNumPreRequest(int numPreRequest) {
		this.numPreRequest = numPreRequest;
	}

	public int getPreRequest(int index) {
		return preRequest[index];
	}

	public int[] getPreRequest() {
		return preRequest;
	}

	public void setPreRequest(int preRequest, int index) {
		this.preRequest[index] = preRequest;
	}

	public void setPreRequest(int[] preRequest) {
		this.preRequest = preRequest;
	}
}
