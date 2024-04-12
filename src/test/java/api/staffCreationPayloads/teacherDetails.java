package api.staffCreationPayloads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class teacherDetails {

	@JsonIgnore
	private String FileName;
	@JsonIgnore
	private String FilePath;

	@JsonIgnore
	private List<Integer> staffIds;
	@JsonIgnore
	private List<String> staffName;

	private String teacherDetails;

}
