package tests.day3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

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
    public void test1(){
         //accept type is json
        // is---and equal to is same , is more simple than equl to so we use is in here
        given().contentType(ContentType.JSON).pathParam("id","100").//request with path param id=100
                when().get("/employees/{id}").then().statusCode(200)//assert that status code is 200
                .and().assertThat().body("last_name",is("King"));//last name is equals to King

    }
    @Test
    public void getLink(){
    Response response=given().contentType(ContentType.JSON).
            pathParam("id","100").
            when().get("/employees/{id}");//when user makes get request
        response.then().statusCode(200);
    JsonPath jsonPath=response.jsonPath();
    //links.herf[1]-->in the json file, find key links,then finds its children herf and get the first one
    String link=jsonPath.getString("links.href[0]");
        //System.out.println(link);
        assertThat(link,is("http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/employees/100"));
}
    @Test
    public void testFirstAndLast(){
        Response response=given().contentType(ContentType.JSON).
                when().get("/employees/");//when user makes get request
        response.then().statusCode(200);
        JsonPath jsonPath=response.jsonPath();
        String firstLN= jsonPath.getString("items.last_name[0]");
        assertThat(firstLN,is("King"));
        String firstSalary= jsonPath.getString("items.salary[0]");
        assertThat(firstSalary,is("24000"));
        // -1 means last one, so items.last_name[-1] gets the last lastname
        String lastFn= jsonPath.getString("items.last_name[-1]");
        assertThat(lastFn,is("Mourgos"));
        String firstSalary1= jsonPath.getString("items.salary[-1]");
        assertThat(firstSalary1,is("5800"));
    }

}