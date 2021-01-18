
package ComplexJson;

import org.testng.annotations.Test;
import files.payload;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class Ex2_SumValidation {

    // Tc-6 : Verify if Sum of all Course prices matches with Purchase Amount
    @Test
    public void sumOfCourses() {
        
        int sum = 0;
        JsonPath js= new JsonPath(payload.coursePrice());
        int coursesListCount = js.get("courses.size()");
        for(int i =0; i < coursesListCount; i++){
            int price = js.get("courses["+i+"].price");
            int copies = js.get("courses["+i+"].copies");
            int amount  = price * copies;
            sum = sum + amount;
        }
        System.out.println("Sum of Course Price :"+ sum);
        int purchaseAmount = js.get("dashboard.purchaseAmount");
        Assert.assertEquals(purchaseAmount,sum );
    }
}
