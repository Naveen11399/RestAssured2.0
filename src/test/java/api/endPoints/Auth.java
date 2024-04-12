package api.endPoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Auth {
	
	  static ResourceBundle properties;
	  

      
      
	@BeforeSuite
	 public static String getToken() throws JSONException {
		
		  properties=ResourceBundle.getBundle("config");
		  String userName = properties.getString("userName");
	      String password = properties.getString("password");
	      String instituteCode = properties.getString("instituteCode");
	      int deviceType = Integer.parseInt(properties.getString("deviceType"));
		
	   	JSONObject requestBody = new JSONObject();
	    requestBody.put("userName", userName);
	    requestBody.put("password", password);
	    requestBody.put("instituteCode", instituteCode);
	    requestBody.put("deviceType", deviceType);
		
	        Response response = given()
	                .contentType(ContentType.JSON)
	                .accept(ContentType.JSON)
	                .body(requestBody.toString())
	                .when()
	                .post(Routes.Post_URL);

	        // Extract token from response body using JSONPath
	        String token = response.jsonPath().getString("data.token");

	        return token;
	    }

	
	   
	   }