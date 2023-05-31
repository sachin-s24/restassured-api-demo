import files.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(PayLoad.coursePrice());

        //print number of courses
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //print purchase amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //print title of the first course
        String courseTitle = js.getString("courses[0].title");
        System.out.println(courseTitle);

        //print all course titles and their respective prices
        for(int i=0; i < count; i++) {
            String Title = js.getString("courses["+i+"].title");
            String Price = js.getString("courses["+i+"].price");
            System.out.println(Title + " "+ Price);

        }

    }
}
