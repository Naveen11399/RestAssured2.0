package api.marksTest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.marksEndPoints.UploadMarksEndPoints;
import api.marksPayload.MarksPojo;
import api.marksPayload.reportDetails;
import io.restassured.response.Response;

public class UploadMarksTest {

	MarksPojo marksPojo;

	reportDetails report;

	Faker faker;

	@Test(priority = 1)
	public void GetExam() {

		Response response = UploadMarksEndPoints.GetExam();

		String gradeId = response.jsonPath().getString("data[0].examReport.gradeId");
		String sectionId = response.jsonPath().getString("data[0].examReport.sectionId");
		String examId = response.jsonPath().getString("data[0].examReport.examId");
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
	public void ExcelInfoTest() {

		Response response = UploadMarksEndPoints.ExcelInfo(marksPojo.getGradeId(), marksPojo.getSectionId(),
				marksPojo.getFileName(), marksPojo.getFilePath());
//		 response.then().log().body();
		Assert.assertEquals(200, response.statusCode());
		String reportDetailsList = response.jsonPath().getString("data.reportDetails");
		
		report=new reportDetails();
          
		report.setReportDetails(reportDetailsList);
		marksPojo.setReportDetails(report);
		
     
    }

		
//		marksPojo.setReportDetails(report);

	

	@Test(priority = 5)
	public void ImportMark() {

		marksPojo.setTitle(faker.lorem().word());

		System.out.println("Request Body: " + marksPojo.toString());

		Response response = UploadMarksEndPoints.MarkImport(marksPojo);

		response.then().log().body();

	}

}
