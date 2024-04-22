package api.marksEndPoints;

import static  io.restassured.RestAssured.given;

import java.io.File;

import com.aventstack.extentreports.gherkin.model.Given;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import api.endPoints.Auth;
import api.endPoints.Routes;
import api.marksPayload.MarksPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UploadMarksEndPoints {

	
	public static Response GetExam() {
		Response response =
				           given()
				           .auth()
				           .oauth2(Auth.getToken())
				           .queryParam("gradeId", "3")
				           .queryParam("sectionId", "1")
				           .queryParam("examId", "4")
				           .queryParam("type", "2")
				           .when()
				           .get(Routes.GetExam_URL);
	
		
		
		return response ;
	}
	
	
	public static Response downloadFile(String gradeId, String sectionId, String examId) {
		Response response=
				given()
				.auth()
				.oauth2(Auth.getToken())
				.queryParam("gradeId",gradeId )
				.queryParam("sectionId", sectionId)
				.queryParam("examId", examId)
				.when()
				.get(Routes.DownloadFile_URL);;
		
		return response;
	}
	
	
	public static Response UploadMark() {
		
		File file=new File(System.getProperty("user.dir")+"/testdata/ExamMultiSubject_export (51).xlsx");
		Response response =
				given()
				.auth()
				.oauth2(Auth.getToken())
				.contentType(ContentType.MULTIPART)
				.multiPart("file",file)
				.accept(ContentType.JSON)
				.when()
				.post(Routes.MarkUpload_URL);
		
		return response;
	}
	
	public static Response ExcelInfo (String gradeId, String sectionId, String fileName, String filePath) {
		Response response=
				given()
				.auth()
				.oauth2(Auth.getToken())
				.queryParam("gradeId",gradeId )
				.queryParam("sectionId", sectionId)
				.queryParam("fileName", fileName)
				.queryParam("filePath", filePath)
				.when()
				.get(Routes.Excelinfo_URL);;
		
		return response;
	}
	
	
	
	public static Response MarkImport(MarksPojo pojo) throws JsonProcessingException {
		 

      ObjectMapper mapper = new ObjectMapper();
      String requestBody = mapper.writeValueAsString(pojo);

// Print the request body
       System.out.println("Request Body: " + requestBody);
       
       
		Response response= 
				given()
				.auth()
				.oauth2(Auth.getToken())
				.contentType(ContentType.JSON)
				.accept(ContentType.ANY)
				.body(pojo)
				.when()
				.post(Routes.BulkMarkImport_URL);
		
		return response;
	}
	
	
	public static Response GetSubjectList(String gradeId, String sectionId, String examId, String type) {
		Response response=
				given()
				.auth().oauth2(Auth.getToken())
				.queryParam("gradeId",gradeId )
				.queryParam("sectionId", sectionId)
				.queryParam("examId", examId)
				.queryParam("type", type)
				.when()
				.get(Routes.GetSubjectlist_URL);
		
		
		return response;
		
	}
	
	public static Response ExamReportDetails(Integer examId) {
		Response response=
				given()
				.auth().oauth2(Auth.getToken())
				.pathParam("examId", examId)
				.when()
				.get(Routes.ExamDetails_URL);
		
		
		return response;
		
	}
	
	public static Response DeleteMarks(Integer marksId) {
		Response response=
				given()
				.auth().oauth2(Auth.getToken())
				.pathParam("marksId", marksId)
				.when()
				.delete(Routes.DeleteMarks_URL);
		
		
		return response;
		
	}
	
	
}
