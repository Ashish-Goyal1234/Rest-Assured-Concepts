package basicsRestAssured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Ex1 {

    public static void main(String[] args) {
            RestAssured.baseURI = "https://rahulshettyacademy.com";
            given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
            .body("{\r\n" + 
                    "  \"location\": {\r\n" + 
                    "    \"lat\": -37.383494,\r\n" + 
                    "    \"lng\": 83.427362\r\n" + 
                    "  },\r\n" + 
                    "  \"accuracy\": 50,\r\n" + 
                    "  \"name\": \"Ashish Rajesh Goyal\",\r\n" + 
                    "  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
                    "  \"address\": \"29, sinhgad layout, cohen 09\",\r\n" + 
                    "  \"types\": [\r\n" + 
                    "    \"shoe park\",\r\n" + 
                    "    \"shop\"\r\n" + 
                    "  ],\r\n" + 
                    "  \"website\": \"http://gmail.com\",\r\n" + 
                    "  \"language\": \"Hindi-IN\"\r\n" + 
                    "}\r\n" + 
                    "")
            .when().post("maps/api/place/add/json")
            .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)");
    }

}
