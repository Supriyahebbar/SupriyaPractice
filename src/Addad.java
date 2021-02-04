import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import Pojo.Serial;
import Pojo.location;

public class Addad {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Serial a=new Serial();
		  a.setAccuracy(50);
		  a.setAddress("29, side layout, cohen 09");
		  a.setLanguage("French-IN");
		  a.setName("Frontline house");
		  a.setPhone_number("(+91) 983 893 3937");
		  a.setWebsite("https://rahulshettyacademy.com");
		  location p=new location();
		 p.setLat(-38.383494);
		 p.setLng(33.427362);
		 a.setLocation(p);
		 List<String> l=new ArrayList<String>();
		 l.add("shoe park");
		 l.add("shop");
		 a.setTypes(l);
       RequestSpecification d=new RequestSpecBuilder().setBasePath("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").
    		   setContentType(ContentType.JSON).build();
		ResponseSpecification e=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification rese=given().spec(d).body(a);
				Response response=rese.when().post("/maps/api/place/add/json").
		then().spec(e).extract().response();
				String repo1=response.toString();
		System.out.println(repo1);
	}

}
