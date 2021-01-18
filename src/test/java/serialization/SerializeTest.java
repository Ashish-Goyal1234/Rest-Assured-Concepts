
package serialization;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import io.restassured.RestAssured;

public class SerializeTest {

    public static void main(String[] args) {
        RestAssured.baseURI ="https://rahulshettyacademy.com";
        
        AddPlace p = new AddPlace();
        Location l = new Location();
        
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("ashish");
        
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);
        
        
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        
       String response =  given().queryParam("key", "qaclick123")
        .body(p)
        .when().post("/maps/api/place/add/json")
        .then().assertThat().statusCode(200).extract().response().asString();
       System.out.println(response);
    }
}
