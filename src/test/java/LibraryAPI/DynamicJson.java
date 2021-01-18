package LibraryAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import files.ReusableMethod;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class DynamicJson {
    String id="";
    @Test(priority=0)
    public void addBook(){
        RestAssured.baseURI="http://216.10.245.166";
        
        String response = given().header("Content-Type", "application/json")
        .body(payload.addBook("Aname", 1001, "Ashish"))
        .when().post("/Library/Addbook.php")
        .then().log().all().assertThat().statusCode(200).body("Msg", equalTo("successfully added"))
        .extract().response().asString();
        
        JsonPath js = ReusableMethod.rawToJson(response);
         id = js.getString("ID");
        System.out.println(id);
    }
    
    @Test(priority=1)
    public void deleteBook(){
        RestAssured.baseURI="http://216.10.245.166";
        
        given().header("Content-Type", "application/json")
        .body("{\r\n" + 
                "\"ID\" : \""+id+"\"\r\n" + 
                "} \r\n" + 
                "")
        .when().post("/Library/DeleteBook.php")
        .then().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"));
    }
}
