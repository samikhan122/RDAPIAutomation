package RSAPI;

import RSAPI.PayloadAddPlace;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) {


        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(PayloadAddPlace.AddPlace())
                .when().post("maps/api/place/add/json").then().log().all().statusCode(200)
                .body("scope", equalTo("APP")).extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); // converts a string into a json for parsing json
        String placeID = js.get("place_id");

        System.out.println(placeID); // to extract the place id to use it for getting place.


        //update place with a new address using put http method
        String newAddress = "70 Summer walk, USA";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeID + ",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").
                when().put("maps/api/place/update/json") //where a response id
                .then().assertThat().log().all().statusCode(200);

        //get place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().asString();

        JsonPath js1 = new JsonPath(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        System.out.println(getPlaceResponse);


        Assert.assertEquals(actualAddress,newAddress);

    }
}
