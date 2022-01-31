package tests.day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonpathWithMapsTests {
        /*
          get the employee with id 100
          verify last_name is King
          verify salary is 24000
       */

    @Test
    public void employeeInfoTest(){
        JsonPath jsonPath = given().pathParam("id", 100).
                contentType(ContentType.JSON).
                when().get("http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/employees/{id}").
                jsonPath();
        // jsonPath.prettyPrint();

        Map<String, Object> personInfo = jsonPath.getMap("");
        System.out.println(personInfo);

        System.out.println(personInfo.get("employee_id"));
        System.out.println(personInfo.get("salary"));
        System.out.println(personInfo.get("hire_date"));
        System.out.println(personInfo.get("links"));

        Map<String, String> map = jsonPath.getMap("links[0]");
        System.out.println(map);
    }
    @Test
    public void metaWheatherTest(){
        JsonPath jsonPath=given().queryParam("query","Chicago").
                when().get("https://www.metaweather.com/api/location/search/").jsonPath();
        jsonPath.prettyPrint();
    }
    /*
    call the metawheather api with query param=san
    verify all titles contain string 'san'
     */
    @Test
    public void testNameContains(){
        JsonPath jsonPath=given().queryParam("query","san").
                when().get("https://www.metaweather.com/api/location/search/").jsonPath();
        jsonPath.prettyPrint();

        List<Map<String,String>> list = jsonPath.get("");
        System.out.println(list.size());
        System.out.println(list);

        for (Map<String,String> city:list){
            assertTrue(city.get("title").toLowerCase().contains("san"),city.get("title")+"did not contain title");

        }

    }

}
