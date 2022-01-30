package tests.day2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class LoggingExamples {
    @BeforeAll
    public static void setUp(){
        baseURI="http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/";

    }
    @Test
    public void requestLogging(){
        // see request logging; log.all-->prints everything in the request
        given().log().everything().
                pathParam("id", "3").
                when().get("/regions/{id}").
                then().statusCode(200);

    }
    @Test
    public void test2(){
        //logifError()-->prints if we get a error status code
        //log().isStatusCodeIsEqualsTo(401/sth)-->prints if the status code matches the provided one
        //log().ifValidationFails-->prints if any assertion fails
        given().pathParam("id", "2").
                when().get("/regions/{id}").
                then().log().ifStatusCodeIsEqualTo(200).
                statusCode(200);
    }
    @Test
    public void test3(){
        //both location we use log().everything()
        given().log().everything().
                pathParam("id", "2").
                when().get("/regions/{id}").
                then().log().everything().
                statusCode(200);
    }
}
