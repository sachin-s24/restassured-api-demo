import files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumofCourses(){
        JsonPath js = new JsonPath(PayLoad.coursePrice());
        int count = js.get("courses.size()");
        int sum =0 ;
        for(int i=0;i<count;++i){
            int price = js.get("courses["+i+"].price");
            int copies = js.get("courses["+i+"].copies");
            int amount = price*copies;
            sum = sum + amount;
        }
        System.out.println(sum);
    }

}
