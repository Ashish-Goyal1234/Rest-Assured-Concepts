package JiraAPITesting;

import static io.restassured.RestAssured.given;
import java.io.File;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;


public class Ex1_AddingCommentInIssue {

    @Test
    public void addComment(){
        
        SessionFilter session = new SessionFilter();
        
        RestAssured.baseURI="http://localhost:8080";

        System.out.println("********* Login to Jira Account *****************");
        String loginResponse = given().header("Content-Type", "application/json")
        .body("{ \"username\": \"ashishg120\", \"password\": \"@shishGoyal1234\" }")
        .filter(session)
        .when().post("/rest/auth/1/session")
        .then().log().all().extract().response().asString();
        
        System.out.println("******************** Adding Comment in Issue *****************");
        
        String addCommentResponse = given().pathParam("id", "10002")
        .header("Content-Type","application/json")
        .body("{\r\n" + 
                "    \"body\": \"new comment from Restassured\",\r\n" + 
                "    \"visibility\": {\r\n" + 
                "        \"type\": \"role\",\r\n" + 
                "        \"value\": \"Administrators\"\r\n" + 
                "    }\r\n" + 
                "}")
        .filter(session)
        .when().post("/rest/api/2/issue/{id}/comment")
        .then().assertThat().statusCode(201).extract().response().asString();
        
        JsonPath js = new JsonPath(addCommentResponse);
        String commentID = js.getString("id");
        
        System.out.println("**************** Adding Attachment in Jira Ticket *********************");
        given().header("X-Atlassian-Token","no-check").filter(session)
        .pathParam("id", "10002")
        .header("Content-Type", "multipart/form-data")  // I have written this type of header because this time I am sending multipart file, not JSOn file.
        // So instead of application/Json, I have written content-type as multipart/formdata
        .multiPart("file", new File("E:\\Selenium\\restAssuredConcepts\\src\\test\\java\\JiraAPITesting\\StepsToReproduce.txt"))
        .when().post("/rest/api/2/issue/{id}/attachments")
        .then().log().all().assertThat().statusCode(200);
        
        System.out.println("************** Get Issue **********************");
        String getResponse = given().filter(session).pathParam("id", "10002")
                
                .queryParam("fields", "comment")  // I need to write this line because : By defaukt it will return all the fields in bug, but i am intrested only in Comment field to be return.
        .when().get("/rest/api/2/issue/{id}")
        .then().extract().response().asString();
        System.out.println(getResponse);
        
        JsonPath js1 = new JsonPath(getResponse);
        int commentsCount = js1.get("fields.comment.comments.size()");
        
        for(int i = 0; i<commentsCount; i++){
           String commentIDIssue = js1.get("fields.comment.comments["+i+"].id").toString();
           if(commentIDIssue.equalsIgnoreCase(commentID)){
               String message = js1.get("fields.comment.comments["+i+"].id").toString();
               System.out.println(message);
           }
        }
        
    }
}
