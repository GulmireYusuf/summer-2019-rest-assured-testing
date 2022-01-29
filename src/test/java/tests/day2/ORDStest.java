package tests.day2;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;//* it import everything from class like  given...
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ORDStest {

    // printPeek---we can add .then()... VS printPrint--we can't
    @Test
    public void employeeTableWithID() {
        Response response = given().pathParam("id", "100").
                when().get("http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/employees/{id}");
        response.then().statusCode(200).and().contentType("application/json");
        String responseStr = response.asString();
        assertThat(responseStr,containsString("100"));
        assertThat(responseStr,containsString("King"));
    }
    @Test
    public void employeeFromCountries() {
        Response response = given().pathParam("id", "AR").
                when().get("http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/countries/{id}");
        response.prettyPeek().then().statusCode(200).contentType("application/json");// it also gives assertion;
        assertThat(response.asString(),containsString("AR"));
        assertThat(response.asString(),containsString("Argentina"));
    }
    @Test
    public void employeeFromDepartment() {
        Response response = given().pathParam("id", "2000").
                when().get("http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/departments/{id}");
        response.then().statusCode(404).and().contentType("text/html");
        assertThat(response.header("Content-Type"),is("text/html"));



    }


}