package com.basics;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;

public class Basics {

	public static void main(String[] args) throws IOException {
		//given-all input details
				//when-submit the API
				//then-validate the resonse
				
				//content of the file to String
				//Content of the file can be converted to Byte
				//Byte can be converted to String
				
				
				RestAssured.baseURI="https://rahulshettyacademy.com";
				
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Sri Priya P Kulkarni\\addPlace.json"))))
				.when().post("/maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200);
	}
}
