package RSAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//read all bytes-> readallb
import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test(dataProvider = "bookData")
    public void addBook(String isbn, String aisle) {
        //post always have a header
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().header("Content-Type", "text/plain")
                .body(PayloadAddPlace.AddBook(isbn, aisle)).when()
                .post("/Library/Addbook.php")
                .then().log().all().statusCode(200)
                .extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

        // System.out.println("====Deleting Books======");


    }

    @DataProvider(name = "bookData")
    public Object[][] getData() {
        //array collection of elements
        //multidimensional array = collection of array
        return new Object[][]{{"april", "1234"}, {"may", "56789"}, {"june", "98765"}};
    }

}
