package api.staffTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import api.staffCreationPayloads.StaffCreatePojo;
//import api.staffCreationPayloads.StaffDetails;
import api.staffCreationPayloads.teacherDetails;
import api.staffCreationPayloads.StaffCreatePojo.AvatarInfo;
import api.staffCreationPayloads.StaffCreatePojo.FamilyDetails;
import api.staffCreationPayloads.StaffCreatePojo.IdentificationProof;
import api.staffEndPoints.StaffBulkImportEndPoints;
import api.staffEndPoints.StaffCreate;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class staffBulkImportTest {

	public Logger log = LogManager.getLogger(this.getClass());
	Faker faker;
	StaffCreatePojo payload;

	teacherDetails teacher;

	@Test(dataProvider = "staffBulk", dataProviderClass = DataProviders.class, priority = 1)
	public void UploadFile_Teacher(String employeeId, String typeOfStaff, String staffName, String appointmentDate,
			String dateOfJoin, String employmentType, String maritalStatus, String bloodGroup, String dob,
			String gender, String motherTongue, String presentAddress, String permanentAddress, String mobileNumber,
			String emailId, String subjectHandling, String graduation, String major, String institutionName,
			String dateOfGraduation, String drivingLicenseNumber, String expiryDate, String aadharNumber,
			String panNumber, String fatherName, String fatherContactNumber, String motherName,
			String motherContactNumber, String spouseName, String spouseContactNumber, String emergencyContactNumber,
			String userName, String category) {

		Response response = StaffBulkImportEndPoints.uploadFile();

		String path = response.jsonPath().get("data.path");

		String fileName = response.jsonPath().get("data.name");
		teacher = new teacherDetails();
		teacher.setFileName(fileName);
		teacher.setFilePath(path);
	}

	@Test(priority = 2)
	public void ViewFile_Teacher() {

		Response response = StaffBulkImportEndPoints.ViewFileTeacher(teacher.getFileName(), teacher.getFilePath());

		// response.then().log().body();

		ArrayList<LinkedHashMap<String, Object>> staffDetails = response.jsonPath().get("data.staffDetails");

		JSONArray jsonArray = new JSONArray();

		for (LinkedHashMap<String, Object> details : staffDetails) {

			JSONObject jsonObject = new JSONObject(details);

			jsonArray.put(jsonObject);
		}

		String teacherDetailsss = jsonArray.toString();

		teacher.setTeacherDetails(teacherDetailsss);

	}

	@Test(priority = 3)
	public void importStaff() {
		Response response = StaffBulkImportEndPoints.importTeacher(teacher);

		response.then().log().body();

		List<Integer> staffIds = response.jsonPath().getList("data.id");

		System.out.println(staffIds);

		List<String> staffName = response.jsonPath().getList("data.firstName");
		System.out.println(staffName);

		teacher.setStaffIds(staffIds);

		teacher.setStaffName(staffName);

	}

	@Test(priority = 4)
	public void getBulKImportStaff() {

//		Response response = StaffBulkImportEndPoints.getTeacher(teacher.getStaffIds());

		for (Integer id : teacher.getStaffIds()) {

			Response response = StaffBulkImportEndPoints.getTeacher(id);
			String firstName = response.jsonPath().getString("data.firstName");
			System.out.println("teacher firstName " + id + ": " + firstName);

			String message = response.jsonPath().getString("message");
			System.out.println("Message for student ID " + id + ": " + message);

		}

	}

	@Test(priority = 5)
	public void UpdateStaff() {

		faker = new Faker();

		for (Integer id : teacher.getStaffIds()) {

			payload = new StaffCreatePojo();

//			payload.setFirstName(faker.name().firstName());

			Date dob = faker.date().birthday();

			// Format the date of birth
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDob = sdf.format(dob);

			payload.setFirstName(faker.name().firstName());
			payload.setLastName(faker.name().lastName());
			payload.setContactEmail(faker.internet().emailAddress());
			payload.setContactNumber(faker.phoneNumber().subscriberNumber(10));
			payload.setPresentAddress(faker.address().city());
			payload.setPermanentAddress(faker.address().city());
			payload.setGender(faker.options().option("male", "female"));
			payload.setDob(formattedDob);
			payload.setUserName(faker.name().username());
			payload.setPassword(faker.internet().password());
			payload.setSubjectIds(faker.number().digits(1) + "," + faker.number().digits(1)); // Example for generating
																								// two
																								// subject IDs
			payload.setTeacherCode(faker.code().isbn10());
			payload.setAppointmentDate(formattedDob);
			payload.setDateOfJoin(formattedDob);
			payload.setEmploymentType(faker.options().option("permanent", "temporary")); // Example for generating
																							// employment
																							// type
			payload.setMartialStatus(faker.options().option("single", "married")); // Example for generating martial
																					// status
			payload.setBloodGroup(faker.options().option("A+", "B+", "AB+", "O+")); // Example for generating blood
																					// group
			payload.setMotherTongue(faker.nation().language());
			payload.setIsSamePresentAddress(faker.number().numberBetween(0, 1));
			payload.setIsActive(1); // Assuming isActive is always set to 1
			payload.setStaffType("Teaching Staff"); // Assuming a fixed staff type
			// Generating education details
			payload.setSendMail(true); // Assuming sendMail is always set to true

			// educationDetailsss

			List<StaffCreatePojo.EducationDetail> educationDetailsss = new ArrayList<>();

			StaffCreatePojo.EducationDetail educationDetail = new StaffCreatePojo.EducationDetail();
			educationDetail.setGraduation("Education");
			educationDetail.setMajor("Education");
			educationDetail.setInstitutionStudies("Education");
			educationDetail.setDateOfGraduation(formattedDob);
			// Assuming fileInfo is always empty
			StaffCreatePojo.FileInfo fileInfo = new StaffCreatePojo.FileInfo();
			fileInfo.setFileName(payload.getTimeTableName());
			fileInfo.setFilePath(payload.getTimeTablePath());
			educationDetail.setFileInfo(fileInfo);

			educationDetailsss.add(educationDetail);
			payload.setEducationDetails(educationDetailsss);

			// identificationProof

			IdentificationProof identificationProof = new IdentificationProof();

			identificationProof.setAadharNumber(faker.phoneNumber().subscriberNumber(12));
			identificationProof.setAadharfileInfo(fileInfo);
			identificationProof.setPanNumber(faker.phoneNumber().subscriberNumber(6));
			identificationProof.setPanfileInfo(fileInfo);
			identificationProof.setLicenseNumber(faker.phoneNumber().subscriberNumber(10));
			identificationProof.setExpiryDate(formattedDob);
			identificationProof.setLicenseInfo(fileInfo);
			identificationProof.setTimeTableFileinfo(fileInfo);

			payload.setIdentificationProof(identificationProof);

			// familyDetails

			FamilyDetails familyDetails = new FamilyDetails();
			familyDetails.setFatherName("Father’s " + faker.name().firstName());
			familyDetails.setFatherContactNumber(faker.phoneNumber().subscriberNumber(10));
			familyDetails.setMotherName("Mother’s " + faker.name().firstName());
			familyDetails.setMotherContactNumber(faker.phoneNumber().subscriberNumber(10));
			familyDetails.setSpouseName(faker.name().firstName());
			familyDetails.setSpouseContactNumber(faker.phoneNumber().subscriberNumber(10));
			familyDetails.setEmergencyContact(faker.phoneNumber().subscriberNumber(10));

			payload.setFamilyDetails(familyDetails);

			AvatarInfo avatarInfo = new AvatarInfo();
			avatarInfo.setName(this.payload.getTeacherFile_Name());
			avatarInfo.setPath(this.payload.getTeacherFile_path());
			payload.setAvatarInfo(avatarInfo);

			Response response = StaffBulkImportEndPoints.updateStaff(id, payload);

			// response.then().log().body();

			String message = response.jsonPath().getString("message");
			System.out.println("message: " + message);

			String firstName = response.jsonPath().getString("data.firstName");
			System.out.println("teacher firstName " + id + ": " + firstName);

		} // assertEquals(payload.getStaffName(),
			// response.jsonPath().getString("data.firstName"));

	}

	@Test(priority = 6)
	public void deleteBulKImportStaff() {
		for (Integer id : teacher.getStaffIds()) {

			Response response = StaffBulkImportEndPoints.DeleteBulkTeacher(id);
			// response.then().log().body();
			String message = response.jsonPath().getString("message");
			System.out.println("Message for student ID " + id + ": " + message);
		}
	}

}
