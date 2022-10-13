package RSAPI;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfCourse(){
        int sum = 0;
        JsonPath js = new JsonPath(PayloadAddPlace.CoursePrice());
        int count = js.getInt("courses.size()");
        for(int i =0; i<count; i++){
            int price = js.get("courses["+i+"].price");
            int copies = js.get("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println(amount);
            System.out.println("==============");
            sum = sum + amount;
        }
        System.out.println(sum);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
        Assert.assertEquals(sum, purchaseAmount);
    }

}
