package tests.day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
    public void postANewSpartann() {
        // create a File that we want to send
        File jsonFile = new File("src/test/resources/spartan.json");

        given().contentType(ContentType.JSON).//this means we are sending json as part of the request body
                body(jsonFile).when().post("/api/spartans").
                prettyPeek().then().statusCode(201).
                body("success", equalTo("A Spartan is Born!"));
    }

    /**
     * create a new spartan object
     * verify code 201
     * get the id from the response
     * get the spartan info using the id
     * verify same spartan if is returned
     */
    @Test
    public void postANewSpartann1() {
        String name="Tabriz";
        String gender="Male";
        int phone=2022022022;

        Map<String,Object> spartan=new LinkedHashMap<>();//keep the order as it is
        spartan.put("name",name);
        spartan.put("gender",gender);
        spartan.put("phone",phone);

       Response response = given().log().everything().contentType(ContentType.JSON).//this means we are sending json as part of the request body
                body(spartan).post("/api/spartans");
       response.prettyPeek().then().statusCode(201);
       int id=response.path("data.id");
        System.out.println(id);

        given().pathParam("id",id).when().get("/api/spartans/{id}").then().statusCode(200).
                body("name",is(name)).//is=equalTo in here we can also use equalTo method
                body("gender",is(gender)).
                body("phone",is(phone));


    }
}