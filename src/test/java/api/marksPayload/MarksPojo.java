package api.marksPayload;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarksPojo {
	
	@JsonIgnore
	private String type;
	
	private String title;
	private String gradeId;
	private String sectionId;
	private String examId;


	private String filePath;
	private String fileName;
	
	private String reportDetails;
	
	//private String reportDetails;
	
//	 private List<ReportDetail> reportDetails;
//	 
//	 
//	 public void deserializeReportDetails(String reportDetailsJson) throws IOException {
//	        ObjectMapper objectMapper = new ObjectMapper();
//	        this.reportDetails = objectMapper.readValue(reportDetailsJson, new TypeReference<List<ReportDetail>>() {});
//	    }
//	 
//	 
//	 @Getter
//	 @Setter
//	 
//	public class ReportDetail {
//		    private Student students;
//		    private List<Subject> subjects;
//		    private int status;
//		    private String reason;
//
//		    // Constructor, getter/setter methods
//		}
//	 @Getter
//	 @Setter
//		class Student {
//		    private int id;
//		    private String firstName;
//		    private String studentCode;
//		    private int userId;
//		    private int gradeSectionId;
//		    private String admissionNumber;
//		    private Object avatarInfo;
//
//		    // Constructor, getter/setter methods
//		}
//	 @Getter
//	 @Setter
//		class Subject {
//		    private Object createdBy;
//		    private String createdDate;
//		    private Object modifiedBy;
//		    private Object modifiedDate;
//		    private int id;
//		    private String subjectName;
//		    private String subjectDescription;
//		    private String subjectIdentifier;
//		    private Object subjectCode;
//		    private Object isActive;
//		    private int gSubjectId;
//		    private String Mark;
//		    private String Grade;
//		    private String TotalMark;
//		    private String ExamDate;
//
//
//}
}