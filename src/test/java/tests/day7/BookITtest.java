package tests.day7;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;
import utilities.TokenUtility;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.*;

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
        String email = ConfigurationReader.get("team_member_email");
        String password = ConfigurationReader.get("team_member_password");

        Response response = given().log().everything().
                queryParam("email", email).
                queryParam("password", password).
                when().get("/sign");
        String accessToken = response.path("accessToken");

        response. prettyPeek().
                then().statusCode(200);

       // System.out.println("accessToken = " + accessToken);
    }
    // eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxOTc0IiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.raPyuRcS8xM5eOhEW4qxepwbs9XHPjlV4Xo8CIPxaPs

    /**
     * get all campuses by providing access token
     */
    @Test
    public void getAllCampuses(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxOTc0IiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.raPyuRcS8xM5eOhEW4qxepwbs9XHPjlV4Xo8CIPxaPs";
        given().
                header("Authorization", token).
                when().get("/api/campuses").
                prettyPeek().then().statusCode(200);
    }
    /**
     * Unit Test the token utility with teacher
     */
    @Test
    public void test2(){
        String token = TokenUtility.getToken(TokenUtility.UserType.TEACHER);
        assertThat(token, not(emptyString()));

    }
    /**
     * Unit Test the token utility with team_member
     */
    @Test
    public void  test3(){
        String token = TokenUtility.getToken(TokenUtility.UserType.TEAM_LEADER);
        assertThat(token, not(emptyString()));
    }

}
