package tests.APIShortTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTests {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://ec2-18-208-250-149.compute-1.amazonaws.com:8000";
    }

    @Test
    public void test1(){

         when().get("api/spartans").
                 prettyPeek().then().statusCode(200);
    }
    @Test
    public void test2(){

       Response response = given().pathParam("id","10").
               when().get("api/spartans/{id}");
                response.prettyPeek().then().statusCode(200);

               assertTrue(response.asString().contains("Lorenza"));
    }
    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON).when().get("api/spartans/");
        response.prettyPeek();
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
    }
}
