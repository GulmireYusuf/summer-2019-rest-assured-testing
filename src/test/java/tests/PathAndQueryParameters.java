package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

public class PathAndQueryParameters {

    /* Test case:
        given i create request with parameter
        when i send my request to http.....
        then the respond should contain....
      */
        //1. Path patameters
    @Test
    public void pathParamTest(){
       Response response=given().pathParam("date","2022-01-27").
                         when().get("https://api.exchangerate.host/{date}");//":" param is in the curly braces here
       response.prettyPeek().then().statusCode(200);
       assertTrue(response.asString().contains("ZWL"));
    }
    // 2. Query Parameters: base=USD
    @Test
    public void queryParams(){
        Response response=given().queryParams("base","USD").
                when().get("https://api.exchangerate.host/latst");
        response.prettyPeek().then().statusCode(200);
        assertTrue(response.asString().contains("\"base\":\"USD\""));
    }
    // symbols:
    @Test
    public void symblosQuery(){
        Response response=given().queryParams("base","USD").
                queryParams("symbols","TRY").
                when().get("https://api.exchangerate.host/latst");
        response.prettyPeek();
        String responseStr=response.asString();
        assertTrue(responseStr.contains("USD"));
        assertFalse(responseStr.contains("EUR"));
    }


}
