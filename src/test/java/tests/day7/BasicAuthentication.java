package tests.day7;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BasicAuthentication {
    @Test
    public void preemptive(){
     //one call
        given().
                auth().preemptive().basic("admin","admin").
                when().get("http://the-internet.herokuapp.com/basic_auth").
                then().statusCode(200);
    }
    @Test
    public void challenged(){
        //additional call
       // mostly we use challenge
        given().
                auth().basic("admin","admin").
                when().get("http://the-internet.herokuapp.com/basic_auth").
                then().statusCode(200);
    }

}
