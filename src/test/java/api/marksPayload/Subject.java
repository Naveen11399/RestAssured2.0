package api.marksPayload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subject {

	private Object createdBy;
	private String createdDate;
	private Object modifiedBy;
	private Object modifiedDate;
	private int id;
	private String subjectName;
	private String subjectDescription;
	private String subjectIdentifier;
	private Object subjectCode;
	private Object isActive;
	private int gSubjectId;
	private int Mark;
	private String Grade;
	private int TotalMark;
	private String ExamDate;

}
