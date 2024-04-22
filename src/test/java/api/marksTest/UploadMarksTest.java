package api.marksTest;

import java.io.IOException;
import java.util.ArrayList;
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

import io.restassured.response.Response;

public class UploadMarksTest {

	MarksPojo marksPojo;

	Faker faker;

	@Test(priority = 1)
	public void GetExam() {

		Response response = UploadMarksEndPoints.GetExam();
		
		String gradeId ="3"; //response.jsonPath().getString("data[0].examReport.gradeId");
		String sectionId ="1" ;//response.jsonPath().getString("data[0].examReport.sectionId");
		String examId = "4";//response.jsonPath().getString("data[0].examReport.examId");
		String type = "2"; // Set the type value manually

		marksPojo = new MarksPojo();

		faker = new Faker();
		Assert.assertEquals(200, response.statusCode());

		marksPojo.setGradeId(gradeId);
		marksPojo.setSectionId(sectionId);
		marksPojo.setExamId(examId);
		marksPojo.setType(type);

	}

	@Test(priority = 2)
	public void DownloadSampleMarkFile() {

		Response response = UploadMarksEndPoints.downloadFile(marksPojo.getGradeId(), marksPojo.getSectionId(),
				marksPojo.getExamId());
		
		Assert.assertEquals(200, response.statusCode());
	}

	@Test(priority = 3)
	public void uploadMarks() {
		Response response = UploadMarksEndPoints.UploadMark();
		Assert.assertEquals(200, response.statusCode());
	
		String filePath = response.jsonPath().getString("data.filePath");
		String fileName = response.jsonPath().getString("data.fileName");

		marksPojo.setFileName(fileName);
		marksPojo.setFilePath(filePath);

	}

	@Test(priority = 4)
	public void ExcelInfoTest() throws IOException {

		Response response = UploadMarksEndPoints.ExcelInfo(marksPojo.getGradeId(), marksPojo.getSectionId(),
				marksPojo.getFileName(), marksPojo.getFilePath());
		
		

		Map<String, Object>[] reportDetailsArray = response.jsonPath().getObject("data.reportDetails", Map[].class);

		marksPojo.setReportDetails(reportDetailsArray);

	}

	@Test(priority = 5)
	public void ImportMark() throws JsonProcessingException {

		marksPojo.setTitle(faker.lorem().word());

		Response response = UploadMarksEndPoints.MarkImport(marksPojo);

		

		Assert.assertEquals(200, response.statusCode());

		String msg = response.jsonPath().getString("message");

		Assert.assertEquals("Data imported successfully", msg);

	}

	@Test(priority = 6)
	public void GetSubjectList() {

		Response response = UploadMarksEndPoints.GetSubjectList(marksPojo.getGradeId(), marksPojo.getSectionId(),
				marksPojo.getExamId(), marksPojo.getType());

//	response.then().log().body();

		Assert.assertEquals(200, response.statusCode());

		List<Integer> examReportId = response.jsonPath().getList("data.examReport.id");

		marksPojo.setExamReportId(examReportId);

		System.out.println("examReportId  :"+examReportId);

	}

	@Test(priority = 7)
	public void ExamReportDetails() {
		
		List<Integer> examMarksId=new ArrayList<>();

		for (Integer examId : marksPojo.getExamReportId()) {

			
			Response response = UploadMarksEndPoints.ExamReportDetails(examId);

//			response.then().log().body();

			Assert.assertEquals(200, response.statusCode());

			List<Integer> examMarksId1 = response.jsonPath().getList("data.id");

			examMarksId.addAll(examMarksId1);
		
		}
		marksPojo.setExamMarksId(examMarksId);

		System.out.println("examMarksId  :"+examMarksId);
	}
	
	
	@Test(priority = 8)
	public void DeleteMarks() {

		for (Integer marksId : marksPojo.getExamMarksId()) {
			
			System.out.println(marksId);

			Response response = UploadMarksEndPoints.DeleteMarks(marksId);

			response.then().log().body();
			
			Assert.assertEquals(200, response.statusCode());

			String msg = response.jsonPath().getString("message");

			Assert.assertEquals("Student marksheet successfully deleted", msg);
		}
	}

}
