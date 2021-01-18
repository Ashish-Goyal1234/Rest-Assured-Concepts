package LibraryAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryManagementMyPractice {
   String url  =  RestAssured.baseURI = "http://216.10.245.166";
  String BookId[]={};
   
    @Test(priority=0, dataProvider="BookInfo")
    public void addBooks(String isbn, int aisle, String authorName){
            String addBookResponse = given().body(payload.addBook(isbn, aisle, authorName))
            .when().post("/Library/Addbook.php")
            .then().log().all().assertThat().statusCode(200).header("Server", "Apache").body("Msg", equalTo("successfully added"))
            .extract().response().asString();
            
            JsonPath js = new JsonPath(addBookResponse);
             BookId = js.get("id");
}
    
    @Test(priority=1)
    public void getBooks(){
        System.out.println("Books as Follows :" + BookId);
      /*  given().queryParam("AuthorName", "bb44")
        .when().get("/Library/GetBook.php")
        .then().log().all().statusCode(404);*/
    }
    
    @DataProvider(name="BookInfo")
    public Object[][] getData(){
        return new Object[][] { {"C", 1001, "Ashish"}, {"Java", 1002, "Parker"}, {"Python", 1003, "Lisa"} };
    }
}
