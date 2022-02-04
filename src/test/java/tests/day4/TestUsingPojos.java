package tests.day4;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Employee;
import pojos.Job;

import java.util.EmptyStackException;
import java.util.List;

import static io.restassured.RestAssured.*;
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
    @Test
    public void test(){
        Job job=new Job("01","accounting",100,100000);
        System.out.println(job);
    }
    @Test
    public void getManyJobs(){
        Response response=when().get("/jobs");
        response.then().statusCode(200);
        //response.prettyPrint();
        //write the jsonpath that matches all the jobs in the response
        //job.class-->which pojo class do you want to convert to
        //convert all the job objects into the new list
        // list contains job type
        List<Job> jobs = response.jsonPath().getList("items",Job.class);
        System.out.println(jobs.get(0));
        System.out.println(jobs.get(1));
    }
    // get the number of employees
    @Test
    public void allTheEmployeeCount(){
        Response response=given().when().get("/employees");
        response.then().assertThat().statusCode(200);

        List<Employee> emps = response.jsonPath().getList("items", Employee.class);
        System.out.println(emps.size());

    }

}
