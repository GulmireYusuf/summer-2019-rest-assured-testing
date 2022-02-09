package tests.day8;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class SSLExamples {

    @Test
    public void  badSSL(){
       given().relaxedHTTPSValidation(). //means we just try the server/ and go ahead
        when().get("https://untrusted-root.badssl.com/"). // website dosen't have a certificate
                prettyPeek().then().statusCode(200);
    }
    @Test
    public void  useKeyStore(){
        // in the given trustStore() in here we pass the location of the trust store file and the password of the file
        given().
                //this path to file location of the file like (c://users/downloads/asdkdj.pdf) something like this (it takes string) and password comes from developer or someone
                trustStore("/path/to/file","password").
                when().get("").then().statusCode(200);

        //in the given keyStore() in here we pass the location of the key store file and the password of the file
        given().
                keyStore("/path/to/file","password").//this path to file(it takes string) and password comes from developer or someone
                when().get("").then().statusCode(200);
    }
}
