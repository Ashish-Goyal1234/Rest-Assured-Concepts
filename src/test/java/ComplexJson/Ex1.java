package ComplexJson;

import files.payload;
import io.restassured.path.json.JsonPath;

public class Ex1 {

    public static void main(String[] args) {
        
        JsonPath js = new JsonPath(payload.coursePrice());
        
        // Tc-1 : Print Number of courses returned by API.
             int coursesListCount =    js.getInt("courses.size()");   // It will return number of elements present the courses array.
             System.out.println("Number of courses available :" + coursesListCount);
             
     System.out.println("********************");   
       // Tc-2 : Print Purchase amount
             int purchaseAmount = js.getInt("dashboard.purchaseAmount");
             System.out.println("Purchase amount will be :" + purchaseAmount);
             
     System.out.println("********************");          
       // TC-3. Print Title of the first course
                 String firstCourseTitle = js.getString("courses[0].title");
                 System.out.println("First Course Title :" + firstCourseTitle);
                 String firstCourseTitle1 = js.get("courses[0].title");  // get method will act same as getStrng();
     
     System.out.println("********************");              
           // Tc-4 : Print All course titles and their respective Prices
                 for(int i = 0; i<coursesListCount; i++){
                     String coursetitle = js.get("courses["+i+"].title");
                     int coursePrice = js.getInt("courses["+i+"].price");
                     System.out.println("Course Title : "+coursetitle +" \tCourse Price :" + coursePrice);
                 }
      
      System.out.println("********************");                   
          // Tc-5 : Print no of copies sold by RPA Course
              for(int i = 0 ;i < coursesListCount; i++){
                  String coursetitle = js.get("courses["+i+"].title");
                  if(coursetitle.equalsIgnoreCase("RPA")){
                      int copies = js.get("courses["+i+"].copies");
                      System.out.println("Number of copies sold by RPA :" + copies);
                      break;
                  }
              }
    }
}
