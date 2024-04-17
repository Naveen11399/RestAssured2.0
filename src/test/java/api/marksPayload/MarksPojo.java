package api.marksPayload;

import java.util.List;

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
	
	private reportDetails reportDetails;


}
