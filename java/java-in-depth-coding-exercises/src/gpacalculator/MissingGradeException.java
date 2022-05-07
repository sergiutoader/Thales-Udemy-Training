package gpacalculator;

public class MissingGradeException extends Exception {
	private int studentId;
	
	public MissingGradeException(int studentId) {
		this.studentId = studentId;
	}

	public int getStudentId() {
		return studentId;
	}
}
