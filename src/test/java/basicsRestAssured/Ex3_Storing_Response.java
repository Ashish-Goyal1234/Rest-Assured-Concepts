
package basicsRestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import files.payload;
import io.restassured.RestAssured;

public class Ex3_Storing_Response {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.AddPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)    // assertThat() is used for validation Purpose
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();   // By using this command I am able to extract the response.
        
        System.out.println(response);

    }
}
