package tests.day7;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BookITtest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= ConfigurationReader.get("bookitQa1");
    }
    /**
     * verify no sogh message
     */
    @Test
    public void test1(){

        when().get("/campuses").
                prettyPeek().then().statusCode(422);
    }
    /**
     * get access token
     */
    @Test
    public void testAccessToken(){
        // team_member_email
        String email=ConfigurationReader.get("team_member_email");
        String password=ConfigurationReader.get("team_member_password");

        Response response = given().log().everything().
                queryParam("email", email).
                queryParam("password", password).
                when().get("/sign");
        response.prettyPeek().then().statusCode(200);

        String accessToken=response.path("accessToken");
        System.out.println("accessToken= "+accessToken);
    }
}
