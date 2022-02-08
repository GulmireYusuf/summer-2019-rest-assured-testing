package tests.day6;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import pojos.Address;
import pojos.Company;
import pojos.Contact;
import pojos.Students;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.given;

public class CompanyTrainingAPITest {

    @Test
    public static void setUp(){
    RestAssured.baseURI= ConfigurationReader.get("companyAPiBaseURL");
}
/**
 * post a new student
 * verify success message
 */
   // this test will fail, I didn't find the reason .
     @Test
     public void postASstudent(){
         Address address=new Address(1234,"fullerton kpwy","Chicago","IL",6989);
         Company company=new Company("Micro star","12/28/2022","Micro star",address);
         Contact contact=new Contact(41,"yahoo@com","7876767987","yahoo com");
         Students students=new Students(1,"mila","yu",12,"2/5/2021","6/11/2000",
                 "78765","IT","female","2","IT","BB",contact,company);


         given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(students).when().post("student/create").prettyPeek().
                 then().statusCode(201);
     }
}
