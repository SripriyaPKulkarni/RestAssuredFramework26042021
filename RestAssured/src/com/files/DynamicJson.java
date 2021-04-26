package com.files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

public class DynamicJson {

	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle){
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json")
		.body(Payload.addBook(isbn,aisle))
		.when()
		.post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		//Parsing json
		JsonPath js=ReusableMethods.rawToJson(response);
		
		String id=js.get("ID").toString();
		System.out.println(id);
		}
	@DataProvider(name="BooksData")
	public Object[][] getData(){
		//array=collection of elements
		//multidimensional arary=collection of arrays
		return new Object[][] {{"rcb","4002"},{"rcb","4003"},{"rcb","4004"}};
	}
}
