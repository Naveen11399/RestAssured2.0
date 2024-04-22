package api.marksPayload;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
	
	@JsonIgnore
	private List<Integer> examReportId;
	
	@JsonIgnore
	private List<Integer> examMarksId;
	
	
	
	private String title;
	private String gradeId;
	private String sectionId;
	private String examId;


	private String filePath;
	private String fileName;
	
	private Map<String,Object>[] reportDetails;

	

}