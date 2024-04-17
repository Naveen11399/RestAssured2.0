package api.staffCreationPayloads;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffCreatePojo {

	@JsonIgnore
	private String TimeTablePath;
	@JsonIgnore
	private String TimeTableName;
	@JsonIgnore
	private String teacherFile_path;
	@JsonIgnore
	private String teacherFile_Name;

	@JsonIgnore
	private String teacherID;

	@JsonIgnore
	private String staffName;

	private String firstName;
	private String lastName;
	private String contactEmail;
	private String contactNumber;
	private String presentAddress;
	private String permanentAddress;
	private String gender;
	private String dob;
	private String userName;
	private String password;
	private String subjectIds;
	private String teacherCode;
	private String appointmentDate;
	private String dateOfJoin;
	private String employmentType;
	private String martialStatus;
	private String bloodGroup;
	private String motherTongue;
	private Integer isSamePresentAddress;
	private Integer isActive;
	private String staffType;
	private List<EducationDetail> educationDetails;
	private Boolean sendMail;
	private IdentificationProof identificationProof;
	private FamilyDetails familyDetails;
	private AvatarInfo avatarInfo;

	// Getter and Setter methods for all fields (generated by IDE or manually)

	// Inner class for EducationDetail
	@Getter
	@Setter
	public static class EducationDetail {
		private String graduation;
		private String major;
		private String institutionStudies;
		private String dateOfGraduation;
		private FileInfo fileInfo;

		// Getter and Setter methods for all fields (generated by IDE or manually)
	}

	// Inner class for FileInfo
	@Getter
	@Setter
	public static class FileInfo {
		private String fileName;
		private String filePath;

		// Getter and Setter methods for all fields (generated by IDE or manually)
	}

	// Inner class for IdentificationProof
	@Getter
	@Setter
	public static class IdentificationProof {
		private String aadharNumber;
		private FileInfo aadharfileInfo;
		private String panNumber;
		private FileInfo panfileInfo;
		private String licenseNumber;
		private String expiryDate;
		private FileInfo licenseInfo;
		private FileInfo timeTableFileinfo;

		// Getter and Setter methods for all fields (generated by IDE or manually)
	}

	// Inner class for FamilyDetails
	@Getter
	@Setter
	public static class FamilyDetails {
		private String fatherName;
		private String fatherContactNumber;
		private String motherName;
		private String motherContactNumber;
		private String spouseName;
		private String spouseContactNumber;
		private String emergencyContact;

		// Getter and Setter methods for all fields (generated by IDE or manually)
	}

	// Inner class for AvatarInfo
	@Getter
	@Setter
	public static class AvatarInfo {

		private String name;
		private String path;
	}

}
