import org.testng.annotations.Test;

import file.payLoad;
import io.restassured.path.json.JsonPath;

public class complexjsontests {
	public static void main (String[] args)
	{
		
		JsonPath js=new JsonPath(payLoad.complexjsonResp());
		int count=js.get("courses.size()");
		System.out.println(count);
		int purschaseamt=js.get("dashboard.purchaseAmount");
		System.out.println(purschaseamt);
		String courseFirstName=js.getString("courses[0].title");
		System.out.println(courseFirstName);
		
		for (int i=0;i<count;i++) {
			
			System.out.println(js.get("courses["+i+"].title").toString());
			System.out.println(js.get("courses["+i+"].price").toString());
			
			}
		for(int i=0; i<count;i++) {
			String coursename=js.get("courses["+i+"].title");
			if(coursename.equalsIgnoreCase("RPA")) {
				int copies=js.getInt("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		
		
		
	}

}
