package api.payloads;

import lombok.Getter;
import lombok.Setter;


public class studentInfo {
	private String name;

	private String gender;
	private int gradeId;
	private String dob;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(int admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public String getStudentId() {
		return studentId;
	}

	public String setStudentId(String studentId) {
		return this.studentId = studentId;
	}

	private int admissionNumber;
	
	private  String studentId;
}
