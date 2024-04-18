package api.marksTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.marksEndPoints.UploadMarksEndPoints;
import api.marksPayload.MarksPojo;
import api.marksPayload.ReportDetails;
import io.restassured.response.Response;

public class UploadMarksTest {

	MarksPojo marksPojo;

	ReportDetails report;

	Faker faker;

	@Test(priority = 1)
	public void GetExam() {

		Response response = UploadMarksEndPoints.GetExam();

		String gradeId = response.jsonPath().getString("data[0].examReport.gradeId");
		String sectionId = response.jsonPath().getString("data[0].examReport.sectionId");
		String examId = response.jsonPath().getString("data[0].examReport.examId");
		String type = "2"; // Set the type value manually

//		response.then().log().body();

		marksPojo = new MarksPojo();
		report = new ReportDetails();
		faker = new Faker();
		// Assert.assertEquals(200, response.statusCode());

		marksPojo.setGradeId(gradeId);
		marksPojo.setSectionId(sectionId);
		marksPojo.setExamId(examId);
		marksPojo.setType(type);

	}

	@Test(priority = 2)
	public void DownloadSampleMarkFile() {

		Response response = UploadMarksEndPoints.downloadFile(marksPojo.getGradeId(), marksPojo.getSectionId(),
				marksPojo.getExamId());
//		response.then().log().body();
		// Assert.assertEquals(200, response.statusCode());
	}

	@Test(priority = 3)
	public void uploadMarks() {
		Response response = UploadMarksEndPoints.UploadMark();
		Assert.assertEquals(200, response.statusCode());

//		response.then().log().body();

		String filePath = response.jsonPath().getString("data.filePath");
		String fileName = response.jsonPath().getString("data.fileName");

		marksPojo.setFileName(fileName);
		marksPojo.setFilePath(filePath);

	}

	@Test(priority = 4)
	public void ExcelInfoTest() throws IOException {

		Response response = UploadMarksEndPoints.ExcelInfo(marksPojo.getGradeId(), marksPojo.getSectionId(),
				marksPojo.getFileName(), marksPojo.getFilePath());
		response.then().log().body();

		// List<MarksPojo.ReportDetail> reportDetails =
		// response.jsonPath().getList("data.reportDetails",
		// MarksPojo.ReportDetail.class);

//		ObjectMapper objectMapper = new ObjectMapper();
//	        List<MarksPojo.ReportDetail> reportDetails = null;
//	        try {
//	            MarksPojo.ReportDetail[] reportDetailsArray = objectMapper.readValue(response.jsonPath().getList("data.reportDetails").toString(), MarksPojo.ReportDetail[].class);
//	            reportDetails = Arrays.asList(reportDetailsArray);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }  
//		

//		marksPojo.setReportDetails(reportDetails);

		// marksPojo.deserializeReportDetails(response.jsonPath().getString("data.reportDetails"));

		//String reportDetailsJsonString = response.jsonPath().getString("data.reportDetails");

		// report.setReportDetails(reportDetailsJsonString);
		// marksPojo.setReportDetails(reportDetailsJsonString);

//		ObjectMapper mapper = new ObjectMapper();
//		ReportDetails[] reportDetails = mapper.readValue(reportDetailsJsonString, ReportDetails[].class);

		// Set the reportDetails in marksPojo
		Map<String, Object>[] reportDetailsArray = response.jsonPath().getObject("data.reportDetails", Map[].class);

        // Convert the array to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String reportDetailsJson = objectMapper.writeValueAsString(reportDetailsArray);
        String cleanedJsonString = reportDetailsJson.replaceAll("\\\\", "");
		
		marksPojo.setReportDetails(cleanedJsonString);

	}

	@Test(priority = 5, enabled = true)
	public void ImportMark() throws JsonProcessingException {

		marksPojo.setTitle(faker.lorem().word());

		Response response = UploadMarksEndPoints.MarkImport(marksPojo);

		response.then().log().body();

	}

}
