package api.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import api.endPoints.StudentEndPoints;
import api.payloads.StdImportPojo;

import api.utilities.DataProviders;
import io.restassured.response.Response;

public class StdBulkImportTest {

	StdImportPojo studentPayload;

	public Logger log = LogManager.getLogger(this.getClass());

	@Test(dataProvider = "StdBulk", dataProviderClass = DataProviders.class, priority = 1)
	public void testDataFromExcel(String admissionNumber, String admissionDate, String studentName, String gender,
			String dob, String grade, String section, String aadharNumber, String studentEmail, String studentContactNo,
			String address, String secondLanguage, String thirdLanguage, String bloodGroup, String nationality,
			String country, String state, String religion, String community, String previousSchool,
			String previousGrade, String dateOfLeaving, String tcIssuedDate, String reasonOfLeaving, String fatherName,
			String fatherContactNo, String fatherEmailId, String fatherQualification, String fatherIncome,
			String fatherOccupation, String fatherDesignation, String fatherCompanyName, String fatherAadharNo,
			String fatherPassportNo, String fatherCompanyAddress, String motherName, String motherContactNo,
			String motherEmailId, String motherQualification, String motherIncome, String motherOccupation,
			String motherDesignation, String motherCompanyName, String motherAadharNo, String motherPassportNo,
			String motherCompanyAddress, String guardianName, String guardianEmailId, String guardianContactNo,
			String guardianQualification, String guardianIncome, String guardianOccupation, String guardianDesignation,
			String guardianCompanyName, String guardianAadharNo, String guardianPassportNo,
			String guardianCompanyAddress, String communication) {

		log.info("Data Driven Tesing Started");

		Response response = StudentEndPoints.createBulkStudent();

//	
		String path = response.jsonPath().get("data.path");

		String fileName = response.jsonPath().get("data.name");
		studentPayload = new StdImportPojo();

		studentPayload.setFileName(fileName);
		studentPayload.setPath(path);

		log.info("Data Driven Tesing Ended");

	}

	@Test(priority = 2)
	public void viewFileInfo() throws JsonProcessingException {

		log.info("viewFileInfo Testing Started");

		Response response = StudentEndPoints.viewFileInfo(this.studentPayload.getFileName(),
				this.studentPayload.getPath());

		ArrayList<LinkedHashMap<String, Object>> reportDetailsList = response.jsonPath().get("data.reportDetails");

		JSONArray jsonArray = new JSONArray();

		for (LinkedHashMap<String, Object> report : reportDetailsList) {
			// Convert each report details map to JSONObject
			JSONObject jsonObject = new JSONObject(report);

			// Add the JSONObject to the JSONArray
			jsonArray.put(jsonObject);
		}

		// Print the formatted JSONArray

		String jsonArrayString = jsonArray.toString();

		studentPayload.setStudentDetails(jsonArrayString);

		log.info("viewFileInfo Testing Ended");

	}

	@Test(priority = 3)
	public void AddStudentExcel() throws JsonProcessingException {

		Response response = StudentEndPoints.UploadStudent(studentPayload);

		String id = response.jsonPath().getString("data[0].id");

		// Extracting the firstName
		String firstName = response.jsonPath().getString("data[0].firstName");

		String id1 = response.jsonPath().getString("data[1].id");
		String Name1 = response.jsonPath().getString("data[1].firstName");

		List<Integer> ids = response.jsonPath().getList("data.id");
		System.out.println("IDs: " + ids);

		// Extracting the first names
		List<String> firstNames = response.jsonPath().getList("data.firstName");
		System.out.println("First Names: " + firstNames);

		studentPayload.setStudentId(ids);
		studentPayload.setStudentName(firstNames);

	}

	@Test(priority = 4)
	public void getBulkStudentInfo() {
		// Single User
		// Response response =
		// StudentEndPoints.getBulkStudent(this.studentPayload.getStudentId());

		for (Integer id : this.studentPayload.getStudentId()) {
			Response response = StudentEndPoints.getBulkStudent(id);
			// response.then().log().all();
			String name = response.jsonPath().getString("data.studentInfo.name");
			System.out.println("name: " + name);
			String message = response.jsonPath().getString("message");
			System.out.println("Message for student ID " + id + ": " + message);
			// response.then().log().all();
		}

	}

	@Test(priority = 5)
	public void deleteBulkStudent() {

		// Single User
		// Response response =
		// StudentEndPoints.deleteBulkStudent(this.studentPayload.getStudentId());

		for (Integer id : this.studentPayload.getStudentId()) {

			Response response = StudentEndPoints.deleteBulkStudent(id);

			String message = response.jsonPath().getString("message");
			System.out.println("Message for student ID " + id + ": " + message);
			// response.then().log().all();

		}

	}

}
