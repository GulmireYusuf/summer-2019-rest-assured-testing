package tests.day8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utilities.TokenUtility;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static utilities.TokenUtility.UserType.TEAM_MEMBER;

public class OauthExample {
    @Test
    public void  testAllStudents(){
        // get a token
        String token= TokenUtility.getToken(TEAM_MEMBER);
        assertThat(token, not(emptyOrNullString()));
        //get all students
        Response response = given().auth().oauth2(token).
                when().get("/api/students");
                 response.prettyPeek();
                 response.then().statusCode(200).contentType(ContentType.JSON);

        List<String> firstNames = response.jsonPath().getList("firstName");
        System.out.println("firstNames.size() = " + firstNames.size());
    }
}
