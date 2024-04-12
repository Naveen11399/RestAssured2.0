package api.staffEndPoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.gherkin.model.Then;

import api.endPoints.Auth;
import api.endPoints.Routes;
import api.staffCreationPayloads.StaffCreatePojo;
import api.staffTest.StaffCreationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class StaffCreate{
	
	
	public static Response uploadTimeTable() {
		
		File file=new File(System.getProperty("user.dir")+"\\testdata\\Tony Stark wallpapetr.jpg");
		
		Response response=	
            given()
            .contentType("multipart/form-data")
            .multiPart("file", file, "application/json")
            .queryParam("path", "student/")
            .auth()
            .oauth2(Auth.getToken())
            .when()
            .post(Routes.uploadTimeTable_URL);
           
		
		return response;
		
	}
	
	
	public static Response uploadProfile_Happening(Map<String, Object> payload) throws IOException {
	
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .auth().oauth2(Auth.getToken()) // Assuming you have authentication configured
                .when()
                .post(Routes.uploadProfile_Happening_URL);

        // Return the response
        return response;
		
	}
	
	
	
	public static Response uploadProfile_teacher(Map<String, Object> payload) {
		

		
	Response response=	given()
		.contentType(ContentType.JSON)
		.auth().oauth2(Auth.getToken())
		.body(payload)
		.when()
		.post(Routes.uploadProfile_teacher_URL);
	
	
		
		return response;
	}
	
	
	
	public static Response CreateStaff_UI(StaffCreatePojo payload) {
		
		
		Response response=
				given()
				.contentType(ContentType.JSON)
				.auth().oauth2(Auth.getToken())
				.body(payload)
				.when()
				.post(Routes.Create_teacherAPI_URL);
			
		
		
		return response;
	}
	



	public static Response getStaff(String id) {
		
		Response response=given()
		.auth()
        .oauth2(Auth.getToken())
        .pathParam("userId", id)
     	.when()
     	.get(Routes.GetStaff_Details_URL);
		
		return response;
		
	}
	
	public static Response updateStaff(String id,StaffCreatePojo payload) {
		
		Response response=given()
		.accept("application/json, text/plain, */*")
		.contentType(ContentType.JSON)
		.auth()
        .oauth2(Auth.getToken())
        .pathParam("userId", id)
        .body(payload)
     	.when()
     	.put(Routes.Update_Staff_URL);
		
		return response;
		
	}
	

	public static Response deleteStaff(String id) {
		
		Response response=given()
		.auth()
        .oauth2(Auth.getToken())
        .pathParam("userId", id)
     	.when()
     	.delete(Routes.Delete_Staff_URL);
		
		return response;
		
	}
}
