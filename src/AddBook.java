import org.testng.annotations.Test;

import file.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class AddBook {
	public static void main(String[] args) {
		
	
	
		RestAssured.baseURI="http://216.10.245.166";
		String responce= given().log().all().header("Content-Type","application/json").body(payLoad.Addbk()).
		when().post("Library/Addbook.php").then().log().all().statusCode(200).extract().response().asString();
		JsonPath resp= new JsonPath(responce);
		String ID=resp.get("ID");
		
		
	}
}

