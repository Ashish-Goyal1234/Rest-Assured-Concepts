package LibraryAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import files.ReusableMethod;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJsonWithDataProvider {
    
    String id="";
    @Test(priority=0, dataProvider="BooksInfo")
    public void addBook(String isbn, int aisle, String authorName){
        RestAssured.baseURI="http://216.10.245.166";
        
        String response = given().header("Content-Type", "application/json")
        .body(payload.addBook(isbn, aisle, authorName))
        .when().post("/Library/Addbook.php")
        .then().log().all().assertThat().statusCode(200).body("Msg", equalTo("successfully added"))
        .extract().response().asString();
        
        JsonPath js = ReusableMethod.rawToJson(response);
         id = js.getString("ID");
        System.out.println(id);
        deleteBook(id);
    }
    

    public void deleteBook(String id){
        RestAssured.baseURI="http://216.10.245.166";
        
        given().header("Content-Type", "application/json")
        .body("{\r\n" + 
                "\"ID\" : \""+id+"\"\r\n" + 
                "} \r\n" + 
                "")
        .when().post("/Library/DeleteBook.php")
        .then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"));
    }
    
    
    
    @DataProvider(name="BooksInfo")
    public Object[][] getData(){
       return new Object[][] { {"C", 1001, "Ashish"}, {"Java", 1002, "Parker"}, {"Python", 1003, "Lisa"} };
    }
}
