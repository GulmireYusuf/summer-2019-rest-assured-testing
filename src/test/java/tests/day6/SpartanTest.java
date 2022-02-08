package tests.day6;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.Spartan;
import utilities.ConfigurationReader;
import utilities.SpartanApiUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpartanTest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= ConfigurationReader.get("spartanApiBaseURL");

    }
    @Test
    public void createSpartanTest(){
        Faker faker=new Faker();
        String name=faker.name().fullName();
        String gender="Female";
        int phone=2022323212;

        Spartan spartan=new Spartan(gender,name,phone);
        System.out.println(spartan);

        Response response= SpartanApiUtils.createSpartan(spartan);
        response.then().statusCode(201);
        response.then().body("data.name",equalTo(spartan.getName()));
    }

    /**
     * create a spartan
     *then delete spartan
     * verify status code 204
     * call the get spartan api and verify code 404
     */
    @Test
    public void test1(){
        //creating the pojo
       Spartan spartan=SpartanApiUtils.createSpartanObject();
       //send request to api create a spartan
        Response postResponse = SpartanApiUtils.createSpartan(spartan);

        postResponse.then().statusCode(201);

        int id = postResponse.path("data.id");

        given().pathParam("id",id).when().
                delete("/api/spartans/{id}").then().statusCode(204);


        given().pathParam("id",id).when().get("/api/spartans/{id}").then().statusCode(404);

    }
    /**
     *                      PUT TEST
     * update using put
     * create new spartan
     * update the name
     * verify other information is not update
     */
    @Test
    public void putTest(){
        Spartan spartan=SpartanApiUtils.createSpartanObject();
        Response postResponse = SpartanApiUtils.createSpartan(spartan);
        postResponse.then().statusCode(201);

        int id = postResponse.path("data.id");

        spartan.setName("Zoom");
        //send the new info using put
        given().contentType(ContentType.JSON).accept(ContentType.JSON).
                pathParam("id",id).body(spartan).when().
                put("api/spartans/{id}").prettyPeek().then().statusCode(204);

        //verify the updated by getting the spartan by id; verify name is updated; verify other u=info is not uodate
        given().pathParam("id",id).accept(ContentType.JSON).
                when().get("/api/spartans/{id}").
                prettyPeek().then().statusCode(200).body("name",is("Zoom")).
                body("gender",is(spartan.getGender())).
                body("phone",is(spartan.getPhone()));


    }
}
