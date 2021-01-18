package basicsRestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Ex6_Get_GetPlaceValue {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.AddPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)    // assertThat() is used for validation Purpose
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();   // By using this command I am able to extract the response.
        
                System.out.println(response);   // Printing my response in Console.
                
                // For Extracting Response we  use JsonPath Class.
                JsonPath js  = new JsonPath(response);
                String place_id = js.getString("place_id");
                System.out.println("\n Extracted value of place id from Json is :" + place_id);
                
                System.out.println("\n**************Updating Place********************");
                
                String newAddress = "Sinhgad Road Hingne Khurd";
                given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\r\n" + 
                        "\"place_id\":\""+place_id+"\",\r\n" + 
                        "\"address\":\""+newAddress+"\",\r\n" + 
                        "\"key\":\"qaclick123\"\r\n" + 
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).header("Server", "Apache/2.4.18 (Ubuntu)");

                System.out.println("\n ****************getplace********************");
                String  getPlaceResponse =  given().queryParam("key", "qaclick123").queryParam("place_id", place_id)
                 .when().get("maps/api/place/get/json")
                 .then().log().all().assertThat().statusCode(200).extract().response().asString();
                  
                JsonPath js1 = new JsonPath(getPlaceResponse);
                   String actualaddress = js1.getString("address");
                   System.out.println("so the actual updated address is : " + actualaddress);
                   Assert.assertEquals(actualaddress, newAddress);
    }
}
