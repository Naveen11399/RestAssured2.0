package api.marksEndPoints;

import static  io.restassured.RestAssured.given;

import java.io.File;

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
	
	public static Response MarkImport(MarksPojo marksPojo) {
		
		System.out.println("MarkImportuu  :"+marksPojo);
		
		Response response= 
				given()
				.auth()
				.oauth2(Auth.getToken())
				.contentType(ContentType.JSON)
				.accept(ContentType.ANY)
				.body(marksPojo.toString())
				.when()
				.post(Routes.BulkMarkImport_URL);
		
		return response;
	}
	
	
}
