package api.endPoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ResourceBundle;

import api.payloads.StdImportPojo;
import api.payloads.createStudent;
import api.payloads.studentInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentEndPoints2 {

	static ResourceBundle properties = ResourceBundle.getBundle("config");

	studentInfo stdInfo = new studentInfo();

	public static Response createStudent(createStudent create_Std) {
		String BaseUrl = properties.getString("BaseUrl");
		String createStdUrl = properties.getString("Create_Std_URL");
		String url = BaseUrl + createStdUrl;
		;
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).auth()
				.oauth2(Auth.getToken()).body(create_Std).when()

				.post(url);

		return response;

	}

	public static Response getCreatedStudent(String studentId) {
		String BaseUrl = properties.getString("BaseUrl");
		String Get_Created_Std_URL = properties.getString("Get_Created_Std_URL");
		String url = BaseUrl + Get_Created_Std_URL;

		Response response = given().auth().oauth2(Auth.getToken()).pathParam("studentId", studentId).get(url);

		return response;

	}

	public static Response deleteStudent(String studentId) {
		String BaseUrl = properties.getString("BaseUrl");

		String Delete_Std_URL = properties.getString("Delete_Std_URL");
		String url = BaseUrl + Delete_Std_URL;
		Response response = given().auth().oauth2(Auth.getToken()).pathParam("studentId", studentId).delete(url);
		return response;

	}

	public static Response createBulkStudent() {

		File file = new File(System.getProperty("user.dir") + "//testdata//Student-sample-data (1) (1).xlsx");

		Response response = given().contentType("multipart/form-data").multiPart("report", file, "application/json")
				.auth().oauth2(Auth.getToken())

				.when()

				.post(Routes.Std_Bulk_URL + "?path=student-management/imports/");

		return response;

	}

	public static Response viewFileInfo(String fileName, String path) {

		Response response = given().auth().oauth2(Auth.getToken()).queryParam("fileName", fileName)
				.queryParam("filePath", path).when().get(Routes.View_Std_BulK_URL);

		return response;

	}

	public static Response UploadStudent(StdImportPojo studentPayload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).auth()
				.oauth2(Auth.getToken()).body(studentPayload).when()

				.post(Routes.Add_Std_BulK_URL);

		return response;

	}

	public static Response getBulkStudent(Integer id) {

		Response response = given().auth().oauth2(Auth.getToken()).pathParam("studentId", id)
				.get(Routes.Get_Created_Std_URL);

		return response;

	}

	public static Response deleteBulkStudent(Integer id) {
		Response response = given().auth().oauth2(Auth.getToken()).pathParam("studentId", id)
				.delete(Routes.Delete_Std_URL);
		return response;

	}

}
