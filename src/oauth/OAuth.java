package oauth;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import Pojo.api;
import Pojo.getCourses;
import Pojo.getResponse;
import Pojo.webAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;



public class OAuth {
	public static void main(String[] args) {
		String[] excepted= {"Ab", "Cd","EF"};
	
		
String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AY0e-g7hLk0tD2Uxd_LaOMP8uo_vpFxp89hWSBAzXmP7BK4tGi86cV3vk-nvnGygr5jX3Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
		
		String partialcode=url.split("code=")[1];
		   String code=partialcode.split("&scope")[0];
		       
		
		String accessTokenResp = given().urlEncodingEnabled(false).header("Content-Typ","application/x-www-form-urlencoded").queryParams("code", code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "rZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code").log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js=new JsonPath(accessTokenResp);
		 String accessToken=js.getString("access_token");
		 System.out.println(accessToken);
		 
		 
		 
		 getResponse rc=given().queryParam("access_token", "accessToken").expect().defaultParser(Parser.JSON)
		 .when().
		 get("https://rahulshettyacademy.com/getCourse.php").as(getResponse.class);
		List<api> ap=rc.getCourses().getApi();
		int a=ap.size();
		for (int i=0;i<a;i++) {
		if(ap.get(i).getCourseTitle().equalsIgnoreCase("udfbiufhw"))
		{
			System.out.println(ap.get(i).getPrice());
		}
		 ArrayList<String> actual=new ArrayList<String>();
		List<webAutomation> wb=rc.getCourses().getWebAutomation();
		for (int j=0; j<wb.size();j++) {
			actual.add(wb.get(j).getCourseTitle());
			}
		
		List<String> exp=Arrays.asList(excepted);
		Assert.assertEquals(actual, exp);
		

		
	}

}
}
