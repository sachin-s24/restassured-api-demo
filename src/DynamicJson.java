import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public static void addBook(String isbn, String aisle){
        RestAssured.baseURI = "http://216.10.245.166";

        String response = given().header("Content-Type", "application/json").
                body(PayLoad.addBook(isbn , aisle)).
                when().post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).
                body("Msg",equalTo("successfully added")).
                extract().response().asString();
        JsonPath js = new JsonPath(response);
        String id = js.get("ID");

        System.out.println(id);
    }


    @DataProvider(name="BooksData")

    public Object[][] getData() {
        return new Object[][] {{"hiuhsa","87979"},{"huuu","7776"},{"uiiu","9899"}};
    }

}
