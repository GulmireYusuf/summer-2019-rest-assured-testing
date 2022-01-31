package tests.day3;

import io.restassured.path.json.JsonPath;
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
}