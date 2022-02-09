package tests.day8;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class SSLExamples {

    @Test
    public void  badSSL(){
       given().relaxedHTTPSValidation(). //means we just try server and go ahead
        when().get("https://untrusted-root.badssl.com/"). // website dosen't have a certificate
                prettyPeek().then().statusCode(200);
    }
}
