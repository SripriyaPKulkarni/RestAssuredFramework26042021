package com.basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.files.Payload;

import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sum_validation(){
		int sum=0;
		//Parsing json object
		JsonPath js=new JsonPath(Payload.Courses());
		
		int count=js.getInt("courses.size()");
		
		for(int i=0;i<count;i++){
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			
			int amount=price*copies;
			
			
			sum=sum+amount;
		}
		System.out.println(sum);
		
		int purchase_amount=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchase_amount);
	}
}
