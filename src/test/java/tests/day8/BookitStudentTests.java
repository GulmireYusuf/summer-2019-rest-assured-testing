package tests.day8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utilities.ConfigurationReader;
import utilities.TokenUtility;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static utilities.TokenUtility.UserType.TEAM_MEMBER;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)// if @BeforeAll is static we can't use this one.
public class BookitStudentTests {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= ConfigurationReader.get("bookitQa1");
    }
    /**
     * get all students from the  /api/students endpoint
     * using token from a team member
     * verify status code 200
     * verify data type json
     */
    @Test
    public void  testAllStudents(){
        // get a token
        String token= TokenUtility.getToken(TEAM_MEMBER);
        //get all students
        assertThat(token, not(emptyOrNullString()));


    }
}
