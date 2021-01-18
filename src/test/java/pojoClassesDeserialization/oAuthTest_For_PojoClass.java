package pojoClassesDeserialization;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class oAuthTest_For_PojoClass {

    public static void main(String[] args) throws InterruptedException {

        String[] courseTitles = {"Selenium webdriver Java" , "Cypress" , "Protractor"};
        
        String url = "https://rahulshettyacademy.com/getCourse.php?state=pune&code=4%2F0AY0e-g5ZRhL2sxtv7-1I899ZWZ39Qmvx5OhOLQ1iPpsQiWKryWPZPanxz4233WH4dX4o7A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println(code);
        
        System.out.println("*************** Get Access Token *************");
        
        String accessTokenResponse = given().queryParams("code",code)
        .urlEncodingEnabled(false)  // Used to stop conversion of e.g: 4%2 in URL otherwise my URL will fail.(used to handle special character or conversion in URL) 
        .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
        .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
        .queryParams("grant_type","authorization_code")
        .when().post("https://www.googleapis.com/oauth2/v4/token").asString();
        
        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");
        
        System.out.println("*************** Getting actual Response ******************");
        GetCourse_Main_Class getCourse = given().queryParam("access_token", accessToken)
                                                                .expect().defaultParser(Parser.JSON)
                                                                .when()
                                                                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse_Main_Class.class);
 
        System.out.println("Get linkedin value :" + getCourse.getLinkedIn());
        System.out.println("Get Instructor name :" + getCourse.getInstructor());
        System.out.println("Get course title of index 1 :" + getCourse.getCourses().getAPI().get(1).getCourseTitle());
        
        System.out.println("*************** Iterating courses Array and getting Price value ******************");
        List<API_SubChild_Class_2> apiCourses = getCourse.getCourses().getAPI();
            for(int i=0; i < apiCourses.size(); i++ ){
                if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI WebServices Testing")){
                    System.out.println(apiCourses.get(i).getPrice());
                }
            }
            
        System.out.println("*************** Get the course name of WebAutomation ******************");
            
            ArrayList<String> a = new ArrayList<String>();
            
            List<WebAutomation_SubChild_Class_1> webAutomationCourse = getCourse.getCourses().getWebAutomation();
            for(int i = 0; i<webAutomationCourse.size();i++ ){
             a.add(webAutomationCourse.get(i).getCourseTitle());
            }
            List<String> expectedList = Arrays.asList(courseTitles);
            Assert.assertTrue(a.equals(expectedList));
    }
}