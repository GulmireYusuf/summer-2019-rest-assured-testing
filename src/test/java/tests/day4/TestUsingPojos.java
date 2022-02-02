package tests.day4;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Job;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestUsingPojos {
    @BeforeAll
    public static void setUp() {
        baseURI = "http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/";
    }
    @Test
    public void getASingle(){
        Response response = given().pathParam("id", "IT_PROG").
                when().get("/jobs/{id}");
        response.then().statusCode(200);
        //response.prettyPrint();
        // convert the body into given type
        Job itJob=response.as(Job.class);
        System.out.println(itJob);
        System.out.println("==================");
        System.out.println("getJob_title= "+itJob.getJob_title());
        System.out.println("gitgetJob_id= "+itJob.getJob_id());
        //verify that job title is programmer
        assertThat(itJob.getJob_title(),equalTo("Programmer"));
    }

}