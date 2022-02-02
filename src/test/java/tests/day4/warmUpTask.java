package tests.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class warmUpTask {
    /*
    when user makes get request to"/employeess" in hr ORDS-HR api
    then user verifies that status code is 200
    and user verifies that avarage salary is greater than 5000
     */

    @Test
    public void test() {
        Response response = given().contentType(ContentType.JSON).
                when().get("http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/employees");
        response.then().statusCode(200);
        List<Integer> salaryList = response.jsonPath().getList("items.salary");
        System.out.println("salaryList= "+salaryList.size());

        //how to get the avarage from a list of ints
        // get the sum
        int total=0;
        for(Integer salary: salaryList){
            total=total+salary;
        }
        //divide by the number of salaries
        int avarage=total/salaryList.size();

        assertThat(avarage,greaterThan(5000));
    }

}
