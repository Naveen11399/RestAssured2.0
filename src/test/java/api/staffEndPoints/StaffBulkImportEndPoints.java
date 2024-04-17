package api.staffEndPoints;

import static io.restassured.RestAssured.given;

import java.io.File;

import api.endPoints.Auth;
import api.endPoints.Routes;
import api.staffCreationPayloads.StaffCreatePojo;
import api.staffCreationPayloads.teacherDetails;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StaffBulkImportEndPoints {

	public static Response uploadFile() {

		File file = new File(System.getProperty("user.dir") + "\\testdata\\Staff-sample-file (8).xlsx");

		Response response = given().contentType(ContentType.MULTIPART).multiPart("file", file, "application/json")
				.accept(ContentType.ANY).queryParam("path", "staff-management/imports/").auth().oauth2(Auth.getToken())
				.when().post(Routes.Upload_FileTeacher_URL);

		return response;

	}

	public static Response ViewFileTeacher(String fileName, String filePath) {

		Response response = given().auth().oauth2(Auth.getToken()).queryParam("fileName", fileName)
				.queryParam("filePath", filePath).when().get(Routes.ViewFile_Teacher_URL);

		return response;

	}

	public static Response importTeacher(teacherDetails teacherData) {

		Response response = given().auth().oauth2(Auth.getToken()).contentType(ContentType.JSON).accept(ContentType.ANY)
				.body(teacherData).when().post(Routes.ImportStaff_URL);

		return response;

	}

	public static Response getTeacher(Integer staffId) {

		Response response = given().auth().oauth2(Auth.getToken()).pathParam("StaffId", staffId).when()
				.get(Routes.getStaffBulk_URL);

		return response;
	}

	public static Response updateStaff(Integer id, StaffCreatePojo payload) {

		Response response = given().accept("application/json, text/plain, */*").contentType(ContentType.JSON).auth()
				.oauth2(Auth.getToken()).pathParam("StaffId", id).body(payload).when().put(Routes.UpdateStaffBulk_URL);

		return response;

	}

	public static Response DeleteBulkTeacher(Integer staffId) {

		Response response = given().auth().oauth2(Auth.getToken()).pathParam("StaffId", staffId).when()
				.delete(Routes.DeleteStaffBulk_URL);

		return response;
	}

}
