package api.payloads;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

//@Getter @Setter
public class StdImportPojo {

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonIgnore
	private String path;
	@JsonIgnore
	private String fileName;

	private String studentDetails;

	private List<Integer> studentId;

	private List<String> studentName;

	public List<Integer> getStudentId() {
		return studentId;
	}

	public void setStudentId(List<Integer> ids) {
		this.studentId = ids;
	}

	public List<String> getStudentName() {
		return studentName;
	}

	public void setStudentName(List<String> firstNames) {
		this.studentName = firstNames;
	}

	public String getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(String studentDetails) {
		this.studentDetails = studentDetails;
	}

}
