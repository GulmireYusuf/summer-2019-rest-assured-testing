package tests.day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Employee;
import pojos.Job;

import java.util.EmptyStackException;
import java.util.List;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ThisIsTestTTT {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://ec2-18-208-250-149.compute-1.amazonaws.com:8000/";
    }

    /**
     * get all spartans
     * verify status code
     */
    @Test
    public void getAllSpartansss() {
        get("/api/spartans").prettyPeek().then().statusCode(200);
    }

    /**
     * get spartan with id 132
     * verify that response
     * content type-application.json
     * verify that response body
     * id:16
     */
    @Test
    public void test1() {
        given().pathParam("id", 16).
                when().get("/api/spartans/{id}").
                prettyPeek().then().statusCode(200).
                contentType(ContentType.JSON).
                body("id", equalTo(16)).
                body("name", equalTo("Sinclair")).
                body("gender", equalTo("Male")).
                body("phone", equalTo(9714460354L));
    }

    /**
     * make a get request to search api
     * query param nameContains : ha
     * verify response status code
     * verify header application/jason
     * verify body : names o dall object returns contain the string
     */
    @Test
    public void test2() {
        given().log().uri().queryParam("nameContains", "ha").
                when().get("/api/spartans/search").
                prettyPeek().then().statusCode(200).
                contentType(ContentType.JSON).
                body("content.name", everyItem(containsStringIgnoringCase("ha")));

    }
}