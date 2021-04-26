package com.basics;

import com.files.Payload;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*1. Print No of courses returned by API
          2.Print Purchase Amount
          3. Print Title of the first course
          4. Print All course titles and their respective Prices
          5. Print no of copies sold by RPA Course
          6. Verify if Sum of all Course prices matches with Purchase Amount*/
		
		//Parsing json object
		JsonPath js=new JsonPath(Payload.Courses());
		
		// Print No of courses returned by API
		int course_count=js.getInt("courses.size()");
		System.out.println(course_count);
		
		//Print Purchase Amount
		int purchase_amount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchase_amount);
		
		//Print Title of the first course
		String first_course_title=js.getString("courses[0].title");
		System.out.println(first_course_title);
		
		//Print All course titles and their respective Prices
		for(int i=0;i<course_count;i++){
			String coursename=js.getString("courses["+i+"].title");
			System.out.println(coursename);
			System.out.println(js.getInt("courses["+i+"].price"));	
		}
		
		//Print no of copies sold by RPA Course
		for(int i=0;i<course_count;i++){
			String coursename=js.getString("courses["+i+"].title");
			if(coursename.equalsIgnoreCase("RPA"))
			{
				int copies=js.getInt("courses["+i+"].copies");
				
				System.out.println("No of copies:"+copies);
			}
		}
	}

}
