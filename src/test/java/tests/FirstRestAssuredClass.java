package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstRestAssuredClass {
    /*
    when i send request to http://api.zippopotam.us/us/60614
    then the status must 200
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("os.version"));
    }
    @Test
    public void verifystatusCode(){
        // response--that is sent by server as result of our requet
        //get--sends the request to given url
        Response response=when().get("http://api.zippopotam.us/us/60614");
        // print the response
        response.prettyPrint();
        //verify the status code
        //verifies that status code matches the provided option
        response.then().statusCode(200);// status code is contains assertion
        assertTrue(response.asString().contains("Chicago"));

    }
    @Test
    public void verifyBody(){
        Response response= when().get("http://api.zippopotam.us/us/60614");
        String bodyStr=response.asString();
        System.out.println(bodyStr);
        assertTrue(bodyStr.contains(" \"state\": \"Illinois\""));
    }
   @Test
   public void verifyHeader(){
       Response response= when().get("http://api.zippopotam.us/us/60614");
       //response.header-->returns the value of the provided header
       String contentType=response.header("Content-Type");
       String date=response.header("Date");

       System.out.println("content type= "+contentType);
       System.out.println("date= "+date);

       assertEquals("application/json",contentType);
       assertTrue(date.contains("2022"));
   }
   @Test
    public void verifyContentType(){
       Response response= when().get("http://api.zippopotam.us/us/60614");
      //response.getContentType()-->returns the content type of the response
       String contentType=response.getContentType();
       System.out.println(contentType);
       //response.getStatusCode()-->returns the status code  of the response
       int statusCode=response.getStatusCode();
       System.out.println(statusCode);

       assertEquals("application/json",contentType);
      // this line will print and also verify status code
       response.prettyPeek().then().statusCode(200);
   }



    }




