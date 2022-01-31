package tests.day1;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PathAndQueryParameters {

    /* Test case:
        given i create request with parameter
        when i send my request to http.....
        then the respond should contain....
      */
    //1. Path patameters
    @Test
    public void pathParamTest() {
        Response response = given().pathParam("date", "2022-01-27").
                when().get("https://api.exchangerate.host/{date}");//":" param is in the curly braces here
        response.prettyPeek().then().statusCode(200);
        assertTrue(response.asString().contains("ZWL"));
    }

    // 2. Query Parameters: base=USD
    @Test
    public void queryParams() {
        Response response = given().queryParam("base", "USD").
                when().get("https://api.exchangerate.host/latst");
        response.prettyPeek().then().statusCode(200);
        assertTrue(response.asString().contains("\"base\":\"USD\""));
    }

    // symbols:
    @Test
    public void symblosQuery() {
        Response response = given().queryParam("base", "USD").
                queryParams("symbols", "TRY").
                when().get("https://api.exchangerate.host/latst");
        response.prettyPeek();
        String responseStr = response.asString();
        assertTrue(responseStr.contains("USD"));
        assertFalse(responseStr.contains("EUR"));
    }

    @Test
    public void testPathAndQueryParams() {
        Response response = given().
                pathParam("date", "latest").
                queryParam("base", "USD").
                queryParam("symbols", "MYR").
                when().get("https://api.exchangerate.host/{date}");

        response.prettyPeek();
    }

    @Test
    public void TwocityVerify() {
        Response response = given().
                queryParam("base", "istanbul").
                queryParam("query", "Chicago").
                when().get("https://www.metaweather.com/api/location/search/?query=istanbul&query=Chicago");
        response.prettyPeek();
        assertTrue(response.asString().contains("Chicago"));

    }

    // positive test case:
    @Test
    public void TwocityVerify2(){
        Response response=given().log().all().
                queryParam("query","istanbul").
                when().get("https://www.metaweather.com/api/location/search");
        response.prettyPeek().then().statusCode(200);
        String responseStr = response.asString();
        assertTrue(responseStr.contains("Istanbul"));
}
    // negative test case:
    @Test
    public void negativewocityVerify2(){
        Response response=given().
                queryParam("query","New New York").
                when().get("https://www.metaweather.com/api/location/search");
        response.prettyPeek().then().statusCode(200);
        String responseStr = response.asString();
        assertTrue(responseStr.contains("New New York"));


    }
}