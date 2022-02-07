package tests.day7;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BasicAuthentication {
    @Test
    public void perrmptive(){

        given().
                auth().preemptive().basic("admin","admin").
                when().get("http://the-internet.herokuapp.com/basic_auth").
                then().statusCode(200);
    }
}
