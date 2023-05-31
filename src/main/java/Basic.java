import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Basic {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        //POST place
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(PayLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String placeID = js.getString("place_id");

        //System.out.println(placeID);

        //UPDATE place
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"place_id\": \"" + placeID + "\",\n" +
                        "    \"address\": \"70 Summer Palace\",\n" +
                        "    \"key\": \"qaclick123\"\n" +
                        "}").
                when().put("maps/api/place/update/json").
                then().log().all().
                assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //GET place
        String newResponse = given().queryParam("key","qaclick123").
                queryParam("place_id",placeID).
                when().get("maps/api/place/get/json").
                then().assertThat().statusCode(200).
                body("address", equalTo("70 Summer Palace")).extract().response().asString();

       JsonPath jns = new JsonPath(newResponse);
         String newAdd =      jns.getString("address");
        System.out.println(newAdd);

    }
}
