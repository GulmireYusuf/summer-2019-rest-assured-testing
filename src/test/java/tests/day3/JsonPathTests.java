package tests.day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class JsonPathTests {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/";
    }

    @Test
    public void moreJsonPath() {
        given().pathParam("id", "1").
                when().get("/regions/{id}").prettyPeek().then().assertThat().statusCode(200).
                and().assertThat().body("region_name", equalTo("Europe"));
    }

    @Test
    public void test1() {
        //accept type is json
        // is---and equal to is same , is more simple than equl to so we use is in here
        given().contentType(ContentType.JSON).pathParam("id", "100").//request with path param id=100
                when().get("/employees/{id}").then().statusCode(200)//assert that status code is 200
                .and().assertThat().body("last_name", is("King"));//last name is equals to King

    }

    @Test
    public void getLink() {
        Response response = given().contentType(ContentType.JSON).
                pathParam("id", "100").
                when().get("/employees/{id}");//when user makes get request
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        //links.herf[1]-->in the json file, find key links,then finds its children herf and get the first one
        String link = jsonPath.getString("links.href[0]");
        //System.out.println(link);
        assertThat(link, is("http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/employees/100"));
    }

    @Test
    public void testFirstAndLast() {
        Response response = given().contentType(ContentType.JSON).
                when().get("/employees/");//when user makes get request
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();

        String firstLN = jsonPath.getString("items.last_name[0]");
        assertThat(firstLN, is("King"));

        String firstSalary = jsonPath.getString("items.salary[0]");
        assertThat(firstSalary, is("24000"));
        // -1 means last one, so items.last_name[-1] gets the last lastname
        String lastFn = jsonPath.getString("items.last_name[-1]");
        assertThat(lastFn, is("Mourgos"));

        String firstSalary1 = jsonPath.getString("items.salary[-1]");
        assertThat(firstSalary1, is("5800"));

        System.out.println("---------------------");
    }
    /*
    Given accept type is JSON
    When users sends a GET request to "/employees"
    Then status code s 200
    And Content type is application/json
    And verify first_name of the employee with employee_id 100 is Lex
     */

    @Test
    public void getValuebasedOnAnotherValue() {
        Response response = given().contentType(ContentType.JSON).
                when().get("/employees/");//when user makes get request
        response.then().statusCode(200).and().contentType("application/json");

        JsonPath jsonPath = response.jsonPath();
        // in the ietms find and element where the employee_id==102 for each go iterate and return to first name
        // item parent call find method and it takes parameters
        String string = jsonPath.getString("items.find{it.employee_id==102}.first_name");
        System.out.println(string);
        assertThat(string, is("Lex"));
    }

    /*

     */
    @Test
    public void testCountries() {
        Response response = given().contentType(ContentType.JSON).
                when().get("countries");
        List<String> actulList = response.jsonPath().getList("items.country_name");
        System.out.println(actulList);

        List<String> expected = Arrays.asList("Argentina", "Australia", "Belgium", "Brazil", "Canada", "Switzerland", "China");
        System.out.println(expected);
        //assertThat(actulList,contains(expected));

    }

    /*
      Given accept type is JSON
      When users sends a GET request to "/employees"
      Then status code s 200
      And Content type is application/json
      And verify all salaries are bigger 100
     */
    @Test
    public void testSalary() {
        Response response = given().contentType(ContentType.JSON).
                when().get("/employees");
       response.then().statusCode(200);
        List<Integer> list = response.jsonPath().getList("items.salary",Integer.class);
        System.out.println(list);
        // make sure every item in the list (each salary) if bigger than 100
        assertThat(list,everyItem(greaterThan(100)));

    }
}