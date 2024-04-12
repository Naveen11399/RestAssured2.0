package api.staffTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.staffCreationPayloads.StaffCreatePojo;
import api.staffCreationPayloads.StaffCreatePojo.AvatarInfo;
import api.staffCreationPayloads.StaffCreatePojo.EducationDetail;
import api.staffCreationPayloads.StaffCreatePojo.FamilyDetails;
import api.staffCreationPayloads.StaffCreatePojo.IdentificationProof;
import api.staffEndPoints.StaffCreate;
import api.utilities.FileToBase64Converter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StaffCreationTest {

	Faker faker;
	StaffCreatePojo payload;

	@Test(priority = 1)
//@Test
	public void uploadTimeTable() {

		Response response = StaffCreate.uploadTimeTable();
		// response.then().log().body();
		// System.out.println("Response: " + response.asString());

		String TimeTablePath = response.jsonPath().get("data.path");
		String TimeTableName = response.jsonPath().get("data.name");

		payload = new StaffCreatePojo();

		payload.setTimeTableName(TimeTableName);

		payload.setTimeTablePath(TimeTablePath);

	}

	@Test(priority = 2)
	public void uploadProfile_Happening() throws IOException {

		Map<String, Object> payload = new HashMap<>();

		// File object
		File file = new File(System.getProperty("user.dir") + "\\testdata\\TaylorTshirt1.png");

		String base64EncodedString = FileToBase64Converter.fileToBase64(file);

		payload.put("image", "data:image/jpeg;base64," + base64EncodedString);

		// Set path field
		payload.put("path", "happenings/");

		Response response = StaffCreate.uploadProfile_Happening(payload);

//		response.then().log().body();
		// Verify the response status code
		assertEquals(200, response.getStatusCode());

		// Verify the response body content
		JsonPath jsonPath = response.jsonPath();
		assertEquals(1, jsonPath.getInt("status"));
		assertEquals("Image successfully uploaded.", jsonPath.getString("message"));

		// Verify the data content

		assertTrue(jsonPath.getString("data.path").startsWith("https://3icms.s3.ap-south-1.amazonaws.com/happenings"));

	}

	@Test(priority = 3)
	public void uploadProfile_teacher() throws IOException {

		File file = new File(System.getProperty("user.dir") + "\\testdata\\TaylorTshirt2.png");

		Map<String, Object> payloadd = new HashMap<>();

		String base64EncodedString = FileToBase64Converter.fileToBase64(file);

		payloadd.put("image", "data:image/jpeg;base64," + base64EncodedString);
		payloadd.put("path", "teacher/");

		Response response = StaffCreate.uploadProfile_teacher(payloadd);

//		response.then().log().body();

		assertEquals(200, response.getStatusCode());

		// Verify the response body content
		JsonPath jsonPath = response.jsonPath();
		assertEquals("Image successfully uploaded.", jsonPath.getString("message"));

		String teacherFile_path = response.jsonPath().get("data.path");
		String teacherFile_Name = response.jsonPath().get("data.image");

		payload.setTeacherFile_Name(teacherFile_Name);
		payload.setTeacherFile_path(teacherFile_path);

	}

	@Test(priority = 4)

	public void StaffCreation() throws JsonProcessingException {

		faker = new Faker();
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
		payload.setSubjectIds(faker.number().digits(1) + "," + faker.number().digits(1)); // Example for generating two
																							// subject IDs
		payload.setTeacherCode(faker.code().isbn10());
		payload.setAppointmentDate(formattedDob);
		payload.setDateOfJoin(formattedDob);
		payload.setEmploymentType(faker.options().option("permanent", "temporary")); // Example for generating
																						// employment
																						// type
		payload.setMartialStatus(faker.options().option("single", "married")); // Example for generating martial status
		payload.setBloodGroup(faker.options().option("A+", "B+", "AB+", "O+")); // Example for generating blood group
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

		Response response = StaffCreate.CreateStaff_UI(payload);

//		response.then().log().body();
		
		String message = response.jsonPath().getString("message");
		System.out.println("message: " + message);


		String staffId = response.jsonPath().getString("data.id");

		payload.setTeacherID(staffId);

		String staffName = response.jsonPath().getString("data.firstName");

		payload.setStaffName(staffName);

	}

	@Test(priority = 5)
	public void getStaffDetails() {

		Response response = StaffCreate.getStaff(this.payload.getTeacherID());

		assertEquals(payload.getStaffName(), response.jsonPath().getString("data.firstName"));
		
		
		String message = response.jsonPath().getString("message");
		System.out.println("message: " + message);

		
//		response.then().log().body();
//		System.out.println("Response: " + response.asString());
	}

	@Test(priority = 6)
	public void UpdateStaff() {

		faker = new Faker();

		payload.setFirstName(faker.name().firstName());

		Response response = StaffCreate.updateStaff(payload.getTeacherID(), payload);

//		response.then().log().body();
		
		
		String message = response.jsonPath().getString("message");
		System.out.println("message: " + message);
		
		//assertEquals(payload.getStaffName(), response.jsonPath().getString("data.firstName"));


	}

	@Test(priority = 7)
	public void deleteStaff() {
		Response response = StaffCreate.deleteStaff(payload.getTeacherID());
		
		//assertEquals(payload.getStaffName(), response.jsonPath().getString("data.firstName"));
		String message = response.jsonPath().getString("message");
		System.out.println("message: " + message);

//		response.then().log().body();

	}

}
