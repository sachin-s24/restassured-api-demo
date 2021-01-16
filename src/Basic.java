import files.PayLoad;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Basic {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(PayLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)");

    }
}
