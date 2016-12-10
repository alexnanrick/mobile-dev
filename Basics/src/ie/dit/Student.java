package ie.dit;

public class Student extends Person implements PublishDetails{
	private String studentID;
	private String courseCode;
	
	public Student(String name, char gender, String studentID, String courseCode) {
		super(name, gender);
		this.studentID = studentID;
		this.courseCode = courseCode;
	}

	@Override
	public String toString() {
		return super.toString() + "Student [studentID=" + studentID + ", courseCode=" + courseCode + "]";
	}

	@Override
	public void confirmDetails(String studentName) {
		System.out.println(studentName + " is enrolled in " + this.courseCode);
		
	}

	@Override
	public void getCourseCode(String courseCode) {
		// TODO Auto-generated method stub
		
	}

}
