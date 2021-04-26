package com.basics;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.files.Payload;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//given-all input details
				//when-submit the API
				//then-validate the resonse
				
				RestAssured.baseURI="https://rahulshettyacademy.com";
				
				String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(Payload.AddPlace())
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
				
				//System.out.println(response);
				
				//Parsing Json to get values
				JsonPath js=new JsonPath(response);
				
			    String placeID=js.getString("place_id");
			    
			    System.out.println(placeID);
			    
			    //Update Place API
			    String newAddress="Bangalore";
			    
			    given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
			    .body("{\r\n" + 
			    		"\"place_id\":\""+placeID+"\",\r\n" + 
			    		"\"address\":\""+newAddress+"\",\r\n" + 
			    		"\"key\":\"qaclick123\"\r\n" + 
			    		"}\r\n" + 
			    		"")
			    .when().put("/maps/api/place/update/json")
			    .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
			    
			    //Get api
			    
			   String getPlaceResponse= given().log().all().queryParam("key","qaclick123")
			    .queryParam("place_id",placeID)
			    .when().get("/maps/api/place/get/json")
			    .then().log().all().assertThat().statusCode(200).extract().response().asString();
			   
			   JsonPath js1=new JsonPath(getPlaceResponse);
			   
			   String actualAddress=js1.getString("address");
			   
			   Assert.assertEquals(actualAddress, newAddress);
			   
			   
			    
			}
	}


