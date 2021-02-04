import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import file.payLoad;

public class AddPLace {
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payLoad.Addplace()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		JsonPath js1=new JsonPath(response);
		String PLaceId=js1.getString("place_id");
		String actualAdd="gb palya bengaluru, india";
		//update the place or address
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n" + 
				"    \"place_id\": \""+PLaceId+"\",\r\n" + 
				"    \"address\": \"gb palya bengaluru, india\", \r\n" + 
				"    \"key\": \"qaclick123\"\r\n" + 
				"}").when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		String response1=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",PLaceId)
		.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();	
		JsonPath js2=new JsonPath(response1);
		  String address1=js2.getString("address");
		  Assert.assertEquals(address1, actualAdd);
		
		
		}

}
