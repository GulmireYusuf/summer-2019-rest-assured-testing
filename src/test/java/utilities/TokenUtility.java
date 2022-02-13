package utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenUtility {
     //enum-->are useful when we want to use one for the given set variables. It reperents set of variable.
    //enum-->just list of varabiles with the type ; predefine type,that type has certain variables names.
   public enum UserType{TEACHER, TEAM_LEADER, TEAM_MEMBER};

    public static String getToken(UserType type) {
        String token=null, email=null, password = null;

        switch (type) {
            case TEACHER:
                email = ConfigurationReader.get("teacher_email");
                password = ConfigurationReader.get("teacher_password");
                break;
            case TEAM_LEADER:
                email = ConfigurationReader.get("team_leader_email");
                password = ConfigurationReader.get("team_leader_password");
                break;
            case TEAM_MEMBER:
                email = ConfigurationReader.get("student_email");
                password = ConfigurationReader.get("student_password");
                break;
        }
        Response response = given().
                queryParam("email", email).
                queryParam("password", password).
                when().get("/sign");
        response.then().statusCode(200);
        token = response.path("accessToken");
        return token;

    }


}
