package tests.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateSpartanss {
    @BeforeAll
    public static void setUp() {
        baseURI = "http://ec2-18-208-250-149.compute-1.amazonaws.com:8000/";
    }

    /**
     * create a new spartan object
     * by passing valid gender,name,phone
     */
    @Test
    public void postANewSpartann(){
        // create a File that we want to send
        File jsonFile=new File("src/test/resources/spartan.json");

        given().contentType(ContentType.JSON).//this means we are sending json as part of the request body
                body(jsonFile).when().post("/api/spartans").
                prettyPeek().then().statusCode(201).
                body("success",equalTo("A Spartan is Born!"));


    }
}
