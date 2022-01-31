package tests.day2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BodyAndHeaderVerification {
    @BeforeAll
    public static void setUp(){
        baseURI="http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/";
}
    @Test
    public void test1() {
        given().pathParam("id", "101").
                when().get("/employees/{id}").prettyPeek().
                then().assertThat().statusCode(200).
                //extract the header value content and verify
                and().header("Content-Type",equalTo("application/json")).
                //extract the value of the key first_name and verify
                and().body("first_name",equalTo("Neena"));
    }
    @Test
    public void test2(){
        JsonPath jsonPath = given().pathParam("id", "101").
                when().get("/employees/{id}").jsonPath();
        // jsonpath -->class used to navigate through json body and extract values
        System.out.println(jsonPath.prettyPrint());

        String first_name = jsonPath.getString("first_name");
        System.out.println(first_name);

        String salary = jsonPath.getString("salary");
        System.out.println(salary);

        System.out.println(jsonPath.getString("links.herf"));

    }
    @Test
    public void managerTest(){
        JsonPath jsonPath = given().pathParam("id", "100").
                when().get("/departments/{id}").jsonPath();

      String manager_id= jsonPath.getString("manager_id");
      System.out.println("manager_id "+manager_id);

        jsonPath = given().pathParam("id", manager_id).
                when().get("/employees/{id}").jsonPath();

        assertThat(jsonPath.getString("first_name"),is("Nancy"));
    }
    @Test
    public void moreJsonPath(){
        JsonPath jsonPath = when().get("/countries/").jsonPath();
        String allCountries=jsonPath.getString("items.country_name");
        System.out.println(allCountries);
        // get all countries in list of strings
        List<Object> countriesList = jsonPath.getList("items.country_name");
        System.out.println(countriesList.size());

        // get the first country in the list
        String countryNumberOne = jsonPath.getString("items.country_name[1]");
        System.out.println(countryNumberOne);

        // get all country ids
        List<Integer> ids = jsonPath.getList("items.region_id");
        System.out.println(ids);


    }
}