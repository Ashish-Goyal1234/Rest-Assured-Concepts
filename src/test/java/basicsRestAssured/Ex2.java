
package basicsRestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import files.payload;
import io.restassured.RestAssured;

public class Ex2 {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
                given().log().all().queryParam("key", "qaclick123")   // log().all() : Is used to log the given() console
                .header("Content-Type", "application/json")
                .body(payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all()                                                             // log().all() : Is used to log the response in console
                .assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)");
    }

}
