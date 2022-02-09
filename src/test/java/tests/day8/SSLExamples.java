package tests.day8;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class SSLExamples {

    @Test
    public void  badSSL(){               //
       given().relaxedHTTPSValidation(). //means we just try the server/ and go ahead
        when().get("https://untrusted-root.badssl.com/"). // website dosen't have a certificate
                prettyPeek().then().statusCode(200);
    }
    @Test
    public void  useKeyStore(){
        // in the given keyStore() in here we pass the location of the trust store file
        given().
                keyStore("/path/to/file","password").//this ketStore and password coming from developer or someone
                when().get("").then().statusCode(200);
    }
}
