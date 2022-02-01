package tests.day3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class dataDrivenTesting {
    @BeforeAll
    public static void setUp() {
        baseURI = "http://ec2-18-208-250-149.compute-1.amazonaws.com:1000/ords/hr/";
    }
    //junit 5 annotations for data driven tests,in junit 5 data came from multiple source
    //the source is indicated next to the parameterizedTest tag
    //means data is given with the test method
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4}) // ValueSource-->data is given with the test method
    public void validateRegionsNameTest1(int id) {
        given().pathParam("id", id).
                when().get("/regions/{id}").
                prettyPeek().then().assertThat().statusCode(200).
                and().assertThat().body("region_id", equalTo(id));
    }
    //CsvSource is 2d data
    @ParameterizedTest
    @CsvSource ({
            "1,Europe",
            "2, Americas",
            "3, Asia",
            "4, Middle East and Africa"})
    public void validateRegionsNameTest2(int id,String name){
        given().pathParam("id",id).
                when().get("/regions/{id}").
                prettyPeek().then().assertThat().statusCode(200).
                and().assertThat().body("region_id",equalTo(id));

    }
    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv")
    public void validateRegionNameTest(int id,String name){
        given().pathParam("id",id).
                when().get("/regions/{id}").
                prettyPeek().then().assertThat().statusCode(200).
                and().assertThat().body("region_id",equalTo(id));

    }
}
